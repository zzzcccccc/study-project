FROM java:8 AS base

#MAINTAINER dreamdeck <www.dreamdeck.cn>

ENV TZ=Asia/Shanghai
ENV JAVA_OPS -server -Xms128m -Xmx256m -XX:CompressedClassSpaceSize=128m -XX:MetaspaceSize=200m -XX:MaxMetaspaceSize=200m

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /dd-visual/dd-security

WORKDIR /dd-visual/dd-security

ADD ./target/study-project-1.0-SNAPSHOT.jar ./

EXPOSE 8081

CMD sleep 10;java ${JAVA_OPS} -Djava.security.egd=file:/dev/./urandom -jar study-project-1.0-SNAPSHOT.jar