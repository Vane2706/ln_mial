FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

EXPOSE 8082

COPY target/ecommerce-*.jar app.jar

# crear carpetas + permisos correctos
RUN mkdir -p /app/images \
    && useradd -m appuser \
    && chown -R appuser:appuser /app

USER appuser

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8082/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]