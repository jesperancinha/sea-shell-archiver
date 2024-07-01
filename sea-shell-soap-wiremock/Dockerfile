FROM eclipse-temurin:21-alpine

WORKDIR /usr/local/bin

COPY sea-shell-soap-service/target/sea-shell-soap-service-jar-with-dependencies.jar sea-shell-soap-service.jar

COPY entrypoint.sh /usr/local/bin/entrypoint.sh

EXPOSE 8090

ENTRYPOINT ["entrypoint.sh"]

CMD ["tail", "-f", "/dev/null"]
