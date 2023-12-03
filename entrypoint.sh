#!/bin/sh
java -jar -Dspring.profiles.active=docker sea-shell-service-spring-web-flux.jar &
java -jar -Dspring.profiles.active=docker sea-shell-service-immutable.jar &
tail -f /dev/null
