#include <fcntl.h>
#include <netinet/in.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include "interface.h"

#define PORT 14740

struct client_info {
    int sockfd;
    pthread_t previous;
    int hasPrevious;
};

void *client_run(void *);
void client_cleanup(void *);

int main(int argc, char **argv) {
    int sockfd;
    struct sockaddr_in serv_addr = {
        .sin_family = AF_INET,
        .sin_port = htons(PORT),
        .sin_addr = {
            .s_addr = INADDR_ANY
        }
    };
    int r;
    struct client_info *client;
    struct sockaddr_in cli_addr;
    int clilen;
    pthread_t thread;
    int hasPrevious = 0;

    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        perror("socket");
        return sockfd;
    }
    if ((r = bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr))) < 0) {
        perror("bind");
        close(sockfd);
        return r;
    }
    listen(sockfd, 5);
    while (1) {
        client = (struct client_info *) malloc(sizeof(struct client_info));
        if (hasPrevious) {
            client->hasPrevious = 1;
            bcopy(&thread, &client->previous, sizeof(pthread_t));
        } else {
            client->hasPrevious = 0;
        }
        if ((client->sockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen)) < 0) {
            perror("accept");
            close(sockfd);
            if (hasPrevious) {
                pthread_join(thread, NULL);
            }
            return client->sockfd;
        }
        pthread_create(&thread, NULL, client_run, client);
        hasPrevious = 1;
    }
    close(sockfd);
    return EXIT_SUCCESS;
}

void *client_run(void *arg) {
    struct client_info *client;
    char buffer[OUTPUT_BUFFER_SIZE];
    int fd;
    int n;

    pthread_cleanup_push(client_cleanup, arg);
    client = (struct client_info *) arg;
    if ((fd = open("/dev/accelerometer", O_RDONLY)) < 0) {
        perror("open");
        fd = client->sockfd;
        client->sockfd = -1;
        close(fd);
        while (1) {
            sleep((unsigned) -1);
        }
    }
    while (1) {
        if ((n = read(fd, buffer, OUTPUT_BUFFER_SIZE)) < 0) {
            perror("read");
            break;
        }
        if ((n = write(client->sockfd, buffer, n)) < 0) {
            perror("write");
            break;
        }
    }
    close(fd);
    fd = client->sockfd;
    client->sockfd = -1;
    close(fd);
    while (1) {
        sleep((unsigned) -1);
    }
    pthread_cleanup_pop(0);
    return NULL;
}

void client_cleanup(void *arg) {
    struct client_info *client;

    client = (struct client_info *) arg;
    if (client->sockfd >= 0) {
        close(client->sockfd);
    }
    if (client->hasPrevious) {
        if (pthread_cancel(client->previous) < 0) {
            perror("pthread_cancel");
        }
        if (pthread_join(client->previous, NULL) < 0) {
            perror("pthread_join");
        }
    }
}
