FROM maven:3.8.6-eclipse-temurin-11-alpine as build
COPY /src /gamestore/src
COPY pom.xml /gamestore/pom.xml
WORKDIR /gamestore
RUN mvn package

FROM tomcat:9.0.68-jre11-temurin-focal
COPY --from=build /gamestore/target/sf-gamestore.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]
