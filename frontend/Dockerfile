 #FROM ubuntu

# RUN apt-get update -y && \
#    apt-get install -y tzdata && \
#    apt-get install apache2 -y

# ADD build/ /var/www/html/

# EXPOSE 80

FROM httpd:2.4

COPY build/ /usr/local/apache2/htdocs/