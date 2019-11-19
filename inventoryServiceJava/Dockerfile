FROM maven:latest as builder
WORKDIR /app
COPY . .
RUN mvn install

FROM openjdk:8-jre-alpine
COPY --from=builder /app/target/*.jar /app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]