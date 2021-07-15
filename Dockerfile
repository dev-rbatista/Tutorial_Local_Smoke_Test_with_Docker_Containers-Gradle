#FROM tomcat:8.5.68-jdk8-openjdk

#ENV APP_PROFILE=prod

#ADD build/libs/project-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

#EXPOSE 8080

FROM tomcat

RUN apt-get update -y

RUN apt-get install -f

ENV APP_PROFILE=prod

ADD /build/libs/*.war /usr/local/tomcat/webapps/

EXPOSE 8080