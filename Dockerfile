# Etapa 1: build da aplicação (Maven + Java 21)
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copia o código fonte e faz o build
COPY src ./src
RUN mvn -q -DskipTests package

# Etapa 2: imagem final só com o JRE e o JAR
FROM eclipse-temurin:21-jre

WORKDIR /app

# copia o jar gerado da etapa de build
COPY --from=build /app/target/*.jar app.jar

# expõe a porta da sua aplicação
EXPOSE 8080

# comando para rodar o Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]