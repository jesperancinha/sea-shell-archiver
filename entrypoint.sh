#!/bin/sh
java -jar sea-shell-soap-service.jar &
java -jar sea-shell-service-spring-web-flux.jar &
java -jar sea-shell-service-immutable.jar &
tail -f /dev/null
