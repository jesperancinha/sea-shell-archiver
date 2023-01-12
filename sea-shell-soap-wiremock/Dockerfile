FROM openjdk:17-alpine

COPY sea-shell-soap-service/target/sea-shell-soap-service-jar-with-dependencies.jar sea-shell-soap-service.jar

COPY entrypoint.sh /usr/local/bin/entrypoint.sh

EXPOSE 8090 8080 8081

ENTRYPOINT ["entrypoint.sh"]

CMD ["tail", "-f", "/dev/null"]
