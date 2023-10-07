FROM openjdk:17

LABEL owner="developer"
LABEL maintainer="developer"
LABEL description="QUARTZ SPRING BOOT"

COPY --chown=java:root target/quartz-spring-boot-*.jar /apps/quartz-spring-boot.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/apps/quartz-spring-boot.jar" ]