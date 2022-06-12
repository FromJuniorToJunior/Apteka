FROM amazoncorretto:17.0.3
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY target/*.jar app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]