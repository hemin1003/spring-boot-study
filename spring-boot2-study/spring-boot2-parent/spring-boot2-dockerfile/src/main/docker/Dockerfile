FROM java:8
VOLUME /tmp/spring-boot2-dockerfile
ADD spring-boot2-dockerfile.jar spring-boot2-dockerfile.jar
RUN bash -c 'touch /spring-boot2-dockerfile.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot2-dockerfile.jar"]