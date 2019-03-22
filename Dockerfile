FROM openjdk:8-jdk-alpine

EXPOSE 8080

COPY target/book-service-0.1.jar .

CMD ["java","-jar","book-service-0.1.jar"]