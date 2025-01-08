FROM amazoncorretto:23-al2023
ARG jar_name

LABEL Owner="r.ryan.rivera@outlook.com"
LABEL Description="Spring Boot Application"

EXPOSE 8082/tcp
COPY target/$jar_name "app.jar"
ENTRYPOINT ["java","-jar","/app.jar"]