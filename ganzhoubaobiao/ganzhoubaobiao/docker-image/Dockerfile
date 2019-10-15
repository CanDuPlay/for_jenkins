FROM java:7u91-jdk
VOLUME /tmp
ADD ganzhoubaobiao.jar  /webapp/ganzhoubaobiao.jar
RUN bash -c 'touch /webapp/ganzhoubaobiao.jar'
RUN echo "Asia/shanghai" > /etc/timezone
ENTRYPOINT ["java"]
CMD ["params1"]
