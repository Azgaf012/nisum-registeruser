# Use the official image as a parent image
FROM openjdk:17-slim as builder

# Set the working directory in the builder container
WORKDIR /usr/src/app

# Copy the source code from your local host to the builder container
COPY . .

# Build the application
RUN ./gradlew build

# Use the official image as a parent image
FROM openjdk:17-slim

# Set the working directory in the new container to /usr/src/app
WORKDIR /usr/src/app

# Copy the jar file from builder to the new container
COPY --from=builder /usr/src/app/build/libs/*.jar ./app.jar

# Set the default command to run your application
CMD ["java", "-jar", "./app.jar"]
