FROM adoptopenjdk/openjdk16:ubuntu-jre

COPY bin/sea-shell-rest-service.jar sea-shell-rest-service.jar
COPY bin/sea-shell-service-immutable.jar sea-shell-service-immutable.jar
COPY bin/sea-shell-soap-service.jar sea-shell-soap-service.jar

COPY docker/entrypoint.sh /usr/local/bin/entrypoint.sh

EXPOSE 8090 8080 8081

ENTRYPOINT ["entrypoint.sh"]

CMD ["tail", "-f", "/dev/null"]
