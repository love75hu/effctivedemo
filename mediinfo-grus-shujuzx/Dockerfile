FROM 172.19.30.186:8000/pub/java-openjdk-runtime:skywalking-agent-8.15.0-java17 as base
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

CMD ["bash", "/app/appRun.sh","--jar","mediinfo-grus-shujuzx.jar"]
