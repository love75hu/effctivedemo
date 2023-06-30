FROM 172.19.30.186:8000/openjdk/openjdk:jdk17-alpine as base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM  172.19.30.186:8000/gradle/gradle:jdk17-alpine AS build
WORKDIR /app
COPY . /app
RUN gradle -b build.gradle -x test clean build

FROM base as final
WORKDIR /app
COPY --from=build /app/build/libs .
COPY --from=build /app/build/resources/main .
ENTRYPOINT ["java","-Dfile.encoding=utf-8","-Dspring.config.location=/app/application.yaml","-jar","mediinfo-grus-shujuzx-java-0.0.1-SNAPSHOT.jar"]

