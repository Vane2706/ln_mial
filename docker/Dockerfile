FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

EXPOSE 8082

COPY target/ecommerce-*.jar app.jar

RUN mkdir -p /app/images

ENTRYPOINT ["java", "-jar", "app.jar"]