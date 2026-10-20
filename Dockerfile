FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw -B clean package -DskipTests

EXPOSE 8080

CMD ["sh", "-c", "java -jar target/*.jar"]