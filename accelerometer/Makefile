obj-m += accelerometer-mock.o
obj-m += accelerometer.o
accelerometer-mock-objs := io-mock.o calculations.o interface.o
accelerometer-objs := io-real.o calculations.o interface.o

all: accelerometer-networkd
	$(MAKE) -C /lib/modules/$(shell uname -r)/build M=$(PWD) modules

clean:
	$(MAKE) -C /lib/modules/$(shell uname -r)/build M=$(PWD) clean

accelerometer-networkd: network.o
	$(CC) -o $@ $^ $(CFLAGS) $(LDFLAGS) -pthread
