SHELL := /bin/bash
GITHUB_RUN_ID ?=123

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
cypress-open:
	cd e2e && yarn && npm run cypress
cypress-electron:
	cd e2e && make cypress-electron
cypress-chrome:
	cd e2e && make cypress-chrome
cypress-firefox:
	cd e2e && make cypress-firefox
cypress-edge:
	cd e2e && make cypress-edge
s-arch-wait:
	bash s_arch_wait.sh
docker-clean:
	docker-compose -p ${GITHUB_RUN_ID} rm -svf -a
	docker network prune -f
docker-action:
	docker-compose -p ${GITHUB_RUN_ID} rm -svf
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml up -d --build --remove-orphans
docker-clean-build-start: docker-clean b docker
dcup-light:
	docker-compose -p ${GITHUB_RUN_ID} up -d postgres localstack
dcd:
	docker-compose -p ${GITHUB_RUN_ID} down
dcup: dcd docker-clean docker s-arch-wait
dcup-full: docker-clean-build-start s-arch-wait
dcup-full-action: docker-clean b docker-action s-arch-wait
local-pipeline: build build-report
