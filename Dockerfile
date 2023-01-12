FROM openjdk:17-alpine

COPY sea-shell-soap-wiremock/sea-shell-soap-service/target/sea-shell-soap-service-jar-with-dependencies.jar sea-shell-soap-service.jar
COPY sea-shell-service-immutable/target/sea-shell-service-immutable.jar sea-shell-service-immutable.jar
COPY sea-shell-service-spring-web-flux/target/sea-shell-service-spring-web-flux.jar sea-shell-service-spring-web-flux.jar

COPY entrypoint.sh /usr/local/bin/entrypoint.sh

EXPOSE 8090 8080 8081

ENTRYPOINT ["entrypoint.sh"]

CMD ["tail", "-f", "/dev/null"]
