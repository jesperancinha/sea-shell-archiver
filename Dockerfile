FROM openjdk:17-alpine

WORKDIR /usr/local/bin

COPY sea-shell-service-immutable/target/sea-shell-service-immutable.jar sea-shell-service-immutable.jar
COPY sea-shell-service-spring-web-flux/target/sea-shell-service-spring-web-flux.jar sea-shell-service-spring-web-flux.jar

COPY entrypoint.sh /usr/local/bin/entrypoint.sh

EXPOSE 8080 8081

ENTRYPOINT ["entrypoint.sh"]

CMD ["tail", "-f", "/dev/null"]
