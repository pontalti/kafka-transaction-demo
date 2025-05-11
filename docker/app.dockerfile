FROM maven:3.9.5-amazoncorretto-21 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package spring-boot:repackage 

FROM openjdk:21-slim
LABEL Gustavo Pontalti
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000
WORKDIR /app
COPY --from=build /home/app/target/kafka_transaction_demo.jar kafka_transaction_demo.jar
EXPOSE 8080 8000
CMD ["sh", "-c", "while ! nc -z kafka_node 9092; do echo 'Aguardando Kafka...'; sleep 1; done; java -jar kafka_transaction_demo.jar"]

