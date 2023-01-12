b: build
build:
	mvn clean install
build-report:
	mvn clean install -Preports jacoco:prepare-agent package jacoco:report
test:
	mvn test
local:
	mkdir -p bin
	cp sea-shell-service-spring-web-flux/sea-shell-rest-service/target/sea-shell-rest-service-*.jar bin/sea-shell-rest-service.jar
	cp sea-shell-soap-wiremock/sea-shell-soap-service/target/sea-shell-soap-service-*-dependencies.jar bin/sea-shell-soap-service.jar
	cp sea-shell-service-immutable/target/sea-shell-service-immutable-*.jar bin/sea-shell-service-immutable.jar
docker:
	docker-compose up
no-test:
	mvn clean install -DskipTests
local-pipeline: build build-report
