FROM eclipse-temurin:17.0.5_8-jre-alpine

RUN mkdir /app
WORKDIR /app

COPY ./build/quarkus-app/lib ./lib
COPY ./build/quarkus-app/app ./app
COPY ./build/quarkus-app/quarkus ./quarkus
COPY ./build/quarkus-app/quarkus-app-dependencies.txt ./

COPY ./build/quarkus-app/quarkus-run.jar ./

CMD ["java", "-jar", "quarkus-run.jar"]

EXPOSE 7071