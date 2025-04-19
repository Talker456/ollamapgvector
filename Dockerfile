FROM openjdk:17-jdk

COPY build/libs/scraper-demo-1.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]