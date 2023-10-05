FROM openjdk:17-jdk-alpine
RUN mkdir /work
WORKDIR /work
COPY /build/libs/waglewagle-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/work/app.jar"]
