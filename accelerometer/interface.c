#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/fs.h>
#include <linux/slab.h>
#include <asm/uaccess.h>

MODULE_LICENSE("Dual MIT/GPL");
MODULE_AUTHOR("Zach Deibert, Nathan Gawith, Cody Moose");
MODULE_DESCRIPTION("An accelerometer driver");

static int __init accelerometer_init(void);
static void __exit accelerometer_exit(void);
static int device_open(struct inode *, struct file *);
static int device_release(struct inode *, struct file *);
static ssize_t device_read(struct file *, char *, size_t, loff_t *);
static ssize_t device_write(struct file *, const char *, size_t, loff_t *);

static int major;
static int counter = 0;
static struct file_operations fops = {
    .read = device_read,
    .write = device_write,
    .open = device_open,
    .release = device_release
};

static int __init accelerometer_init(void) {
    if ((major = register_chrdev(0, "accelerometer", &fops)) < 0) {
        printk(KERN_ALERT "Registering char device failed with %d\n", major);
        return major;
    }
    printk(KERN_INFO "Accelerometer was assigned the major number %d.\n", major);
    printk(KERN_INFO "To connect to the driver, try running:\n");
    printk(KERN_INFO "'mknod /dev/accelerometer c %d 0'.\n", major);
    return 0;
}

static void __exit accelerometer_exit(void) {
    unregister_chrdev(major, "accelerometer");
}

static int device_open(struct inode *inode, struct file *file) {
    try_module_get(THIS_MODULE);
    return 0;
}

static int device_release(struct inode *inode, struct file *file) {
    module_put(THIS_MODULE);
    return 0;
}

static ssize_t device_read(struct file *file, char *buffer, size_t length, loff_t *offset) {
    char *message;
    char alloc[32];
    int bytes_read = 0;

    message = alloc;
    sprintf(message, "Hello, world %d\n", counter++);
    while (--length && *message) {
        put_user(*(message++), buffer++);
        ++bytes_read;
    }
    return bytes_read;
}

static ssize_t device_write(struct file *file, const char *buffer, size_t length, loff_t *offset) {
    return 0;
}

module_init(accelerometer_init);
module_exit(accelerometer_exit);
