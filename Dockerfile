FROM openjdk:21-jdk

WORKDIR /app

COPY target/dynamic-employee-management-system-0.0.1-SNAPSHOT.jar /app/dynamic-employee-management-system-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "dynamic-employee-management-system-0.0.1-SNAPSHOT.jar"]