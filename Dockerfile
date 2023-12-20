# Start with a base image containing Java runtime (Debian-based)
FROM openjdk:17-slim-buster

# The application's jar file
ARG JAR_FILE=build/libs/spring-boot-server-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

RUN <<EOF

apt-get update -y
apt-get install dnsutils -y
apt-get install curl -y
apt-get install iputils-ping -y
apt-get install wget
apt-get install net-tools

EOF

# Run the jar file 
ENTRYPOINT ["java","-jar","/app.jar"]
