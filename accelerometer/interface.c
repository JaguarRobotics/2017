#include <asm/uaccess.h>
#include <linux/cdev.h>
#include <linux/device.h>
#include <linux/fs.h>
#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/slab.h>
#include "calculations.h"

MODULE_LICENSE("Dual MIT/GPL");
MODULE_AUTHOR("Zach Deibert, Nathan Gawith, Cody Moose");
MODULE_DESCRIPTION("An accelerometer driver");

#define FORMAT_BUFFER_SIZE 64
#define OUTPUT_BUFFER_SIZE 64

struct file_read_data {
    char buffer[OUTPUT_BUFFER_SIZE];
    char *ptr;
};

static int __init accelerometer_init(void);
static void __exit accelerometer_exit(void);
static int device_open(struct inode *, struct file *);
static int device_release(struct inode *, struct file *);
static ssize_t device_read(struct file *, char *, size_t, loff_t *);
static ssize_t device_write(struct file *, const char *, size_t, loff_t *);

static dev_t first;
static struct class *cl;
static struct cdev c_dev;
static char outputFormat[FORMAT_BUFFER_SIZE];
static struct file_operations fops = {
    .read = device_read,
    .write = device_write,
    .open = device_open,
    .release = device_release
};

static int __init accelerometer_init(void) {
    int ret;

    if ((ret = alloc_chrdev_region(&first, 0, 1, "accelerometer")) < 0) {
        return ret;
    }
    if ((cl = class_create(THIS_MODULE, "chardrv")) == NULL) {
        unregister_chrdev_region(first, 1);
        return -1;
    }
    if (device_create(cl, NULL, first, NULL, "accelerometer") == NULL) {
        class_destroy(cl);
        unregister_chrdev_region(first, 1);
        return -1;
    }
    cdev_init(&c_dev, &fops);
    if ((ret = cdev_add(&c_dev, first, 1)) < 0) {
        device_destroy(cl, first);
        class_destroy(cl);
        unregister_chrdev_region(first, 1);
        return ret;
    }
    sprintf(outputFormat, "%%d.%%0%dd,%%d.%%0%dd\n", MULTIPLICATIVE_CONSTANT_LOG10, MULTIPLICATIVE_CONSTANT_LOG10);
    return 0;
}

static void __exit accelerometer_exit(void) {
    cdev_del(&c_dev);
    device_destroy(cl, first);
    class_destroy(cl);
    unregister_chrdev_region(first, 1);
}

static int device_open(struct inode *inode, struct file *filp) {
    struct file_read_data *data;

    try_module_get(THIS_MODULE);
    if (!(data = (struct file_read_data *) kmalloc(sizeof(struct file_read_data), GFP_KERNEL))) {
        return -ENOBUFS;
    }
    data->ptr = data->buffer;
    *data->buffer = 0;
    filp->private_data = data;
    return 0;
}

static int device_release(struct inode *inode, struct file *filp) {
    module_put(THIS_MODULE);
    if (filp->private_data) {
        kfree(filp->private_data);
        filp->private_data = NULL;
    }
    return 0;
}

static ssize_t device_read(struct file *filp, char *buffer, size_t length, loff_t *offset) {
    struct file_read_data *data;
    num_t x;
    num_t y;
    int bytes_read = 0;

    if (!filp->private_data) {
        printk(KERN_ALERT "Private data was not allocated on open()\n");
    }
    data = (struct file_read_data *) filp->private_data;
    if (!*data->ptr) {
        x = getXPosition();
        y = getYPosition();
        sprintf(data->buffer, outputFormat, x / MULTIPLICATIVE_CONSTANT, x % MULTIPLICATIVE_CONSTANT, y / MULTIPLICATIVE_CONSTANT, y % MULTIPLICATIVE_CONSTANT);
        data->ptr = data->buffer;
    }
    while (--length && *data->ptr) {
        put_user(*(data->ptr++), buffer++);
        ++bytes_read;
    }
    return bytes_read;
}

static ssize_t device_write(struct file *filp, const char *buffer, size_t length, loff_t *offset) {
    return 0;
}

module_init(accelerometer_init);
module_exit(accelerometer_exit);
