
# Stage 1: Extract Spring Boot layers
FROM eclipse-temurin:21-jre AS extractor

WORKDIR /app

# Copy the packaged JAR
COPY target/*.jar app.jar

# Extract Spring Boot layers
RUN java -Djarmode=tools -jar app.jar extract --layers --launcher --destination extracted


# Stage 2: Runtime
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Copy extracted layers from the extractor stage
COPY --from=extractor /app/extracted/dependencies/ ./
COPY --from=extractor /app/extracted/spring-boot-loader/ ./
COPY --from=extractor /app/extracted/snapshot-dependencies/ ./
COPY --from=extractor /app/extracted/application/ ./

EXPOSE 8081

ENTRYPOINT ["java", "-cp", ".", "org.springframework.boot.loader.launch.JarLauncher"]