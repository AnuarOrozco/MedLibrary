# ---------- Build stage ----------
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# mvn wrapper and pom copy
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# permits and deps
RUN chmod +x mvnw
RUN ./mvnw -B -q dependency:go-offline

# copy code and build
COPY src ./src
RUN ./mvnw -B -q package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
