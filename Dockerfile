FROM alpine:edge
RUN apk --no-cache add openjdk21
WORKDIR /app
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]