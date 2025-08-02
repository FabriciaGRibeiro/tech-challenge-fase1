
FROM maven:3.8-openjdk-17 AS builder

# Define a pasta de trabalho
WORKDIR /app

COPY pom.xml .

# Baixa todas as dependências listadas
RUN mvn dependency:go-offline

# Agora copia todo o resto do código do projeto 
COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

