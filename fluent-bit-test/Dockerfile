# Step 1: Use OpenJDK base image
FROM openjdk:17-jdk-slim AS build

# Step 2: Set working directory inside the container
WORKDIR /app

# Step 3: Copy Gradle Wrapper and other necessary files first
COPY gradlew .
COPY gradle/ gradle/

# Step 4: Copy the rest of the project files
COPY . .

# Step 5: Make Gradle Wrapper executable
RUN chmod +x gradlew

# Step 6: Run Gradle build
RUN ./gradlew clean build -x test

# Step 7: Use a minimal runtime image for the final application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
