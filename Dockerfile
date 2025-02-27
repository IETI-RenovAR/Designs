# Fase 1: Construcción (builder)
FROM maven:3.8.8-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia solo el pom.xml y descarga dependencias antes de copiar el código fuente
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el código fuente
COPY src ./src

# Construye el proyecto
RUN mvn clean package -DskipTests

# Fase 2: Imagen final
FROM openjdk:17-openjdk-slim

WORKDIR /app

# Copia el .jar desde la fase de compilación
COPY --from=builder /app/target/project-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]