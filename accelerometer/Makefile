obj-m += accelerometer-mock.o
obj-m += accelerometer.o
accelerometer-mock-objs := io-mock.o calculations.o interface.o taylor.o
accelerometer-objs := io-real.o calculations.o interface.o taylor.o

all: accelerometer-networkd test
	$(MAKE) -C /lib/modules/$(shell uname -r)/build M=$(PWD) modules

clean:
	$(MAKE) -C /lib/modules/$(shell uname -r)/build M=$(PWD) clean

accelerometer-networkd: network.o
	$(CC) -o $@ $^ $(CFLAGS) $(LDFLAGS) -pthread

%-us.o: %.c
	$(CC) -c -o $@ $^ $(CFLAGS)

test: taylor-us.o test.o
	$(CC) -o $@ $^ $(CFLAGS) $(LDFLAGS)
