# ---------- Etapa 1: build ----------
FROM eclipse-temurin:21-alpine AS build

WORKDIR /app

# Copia todo el código fuente
COPY . .

RUN chmod +x mvnw

# Compila el proyecto sin tests
RUN ./mvnw clean package -DskipTests

# ---------- Etapa 2: runtime ----------
FROM eclipse-temurin:21-alpine

WORKDIR /app

# Copia el JAR generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto que usa Spring Boot
EXPOSE 8095

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]