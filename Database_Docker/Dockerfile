FROM ubuntu

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk-headless && \
    apt-get install wget -y

RUN wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar

EXPOSE 8083
EXPOSE 9092

CMD java -cp ./h2-1.4.200.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists