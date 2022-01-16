FROM openjdk:17

ADD target/*.jar ecommerce.jar

ENTRYPOINT ["java", "-jar", "ecommerce.jar"]
