FROM maven:3.9-eclipse-temurin-26 AS build

#-------- BUILD STAGE ---------------
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

#------- RUNTIME STAGE ---------------
FROM eclipse-temurin:26-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Configuration par défaut pour Docker (peut être surchargée par les variables d'environnement)
ENV JAVA_OPTS="-Xmx256m -XX:+UseG1GC -XX:MaxGCPauseMillis=200"
ENV SPRING_PROFILES_ACTIVE="${SPRING_PROFILES_ACTIVE:-docker}"

# Script d'entrée avec support des variables d'environnement
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-docker} -jar app.jar"]
