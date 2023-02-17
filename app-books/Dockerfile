FROM eclipse-temurin:17.0.5_8-jre-alpine

ARG VERSION_APP=1.0-SNAPSHOT

COPY ./build/install/app-books/lib/app-books-$VERSION_APP.jar ./
COPY ./build/install/app-books/lib ./lib

CMD ["java", "-cp", "app-books-$VERSION_APP.jar:./lib/*", "com.distribuida.Main"]

EXPOSE 7070