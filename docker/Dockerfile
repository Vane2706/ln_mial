FROM eclipse-temurin:17-jdk-jammy

# 📦 Directorio de la aplicación
ENV APP_HOME=/ecommerce-app
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

# 🌐 Puerto de la aplicación
EXPOSE 8082

# 📦 Copiar el jar generado
COPY target/ecommerce-*.jar app.jar

# 👤 Usuario no root (seguridad)
RUN useradd -m appuser && chown -R appuser:appuser $APP_HOME
USER appuser

# 🩺 Healthcheck (opcional si tienes actuator)
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8082/actuator/health || exit 1

# 🚀 Ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]