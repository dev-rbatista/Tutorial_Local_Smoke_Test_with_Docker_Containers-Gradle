# Setup da aplicação no vs do DEI com base de dados Postgres na aws

## Criar conta na aws

## Criar base de dados e instalação do pgAdmin

https://medium.com/swlh/creating-a-postgresql-db-on-aws-and-connecting-it-to-heroku-django-app-29603df20c2a

## Criar diferentes application.properties

application.properties
```
spring.profiles.active=${APP_PROFILE:test}
```

application-test.properties
```
server.port=8080
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb2;DB_CLOSE_ON_EXIT=FALSE
spring.h2.console.path=/h2-console
```

application-prod.properties
```
################
spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://<endpoint>:5432/<databasename>
spring.datasource.username=<masteruser>
spring.datasource.password=<pass>
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
##############
```

## Adicionar dependencias no pom.xml

```
<dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.20</version>
        </dependency>
```

## Meter o wrapper e fazer git clone para vs

Ver colocar variavel de ambiente 

export APP_PROFILE=prod

ou alterar à mão o application.properties de test para prod

## ./mvnw package

## ./mvnw spring-boot:run

e rezar