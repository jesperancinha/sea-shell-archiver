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
	docker-compose -p ${GITHUB_RUN_ID} up --force-recreate -d
no-test:
	mvn clean install -DskipTests
cypress-open:
	cd e2e && yarn && npm run cypress:open:electron
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
docker-stop-all:
	docker ps -a --format '{{.ID}}' | xargs -I {}  docker stop {}
docker-remove-all:
	docker ps -a --format '{{.ID}}' | xargs -I {}  docker rm {}
docker-clean-build-start: docker-clean docker-remove-all b docker
dcd: dc-migration
	docker-compose -p ${GITHUB_RUN_ID} down
dcup-light: dcd
	docker-compose -p ${GITHUB_RUN_ID} up -d sea-shell-soap-legacy
dcup: dcd docker-clean docker s-arch-wait
dcup-full: dcd docker-clean-build-start s-arch-wait
dcup-full-action:dcd docker-clean b docker-action s-arch-wait
local-pipeline: build build-report
upgrade-local:
	sudo apt update
	sudo apt-get -y install python3-pip
	sudo apt-get -y install npm
	sudo npm install --global yarn
	sudo npm install -g n
	sudo npm install -g npm@10.2.3
	sudo n 18
	curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash
	nvm install --lts
revert-deps-cypress-update:
	if [ -f  e2e/docker-composetmp.yml ]; then rm e2e/docker-composetmp.yml; fi
	if [ -f  e2e/packagetmp.json ]; then rm e2e/packagetmp.json; fi
	git checkout e2e/docker-compose.yml
	git checkout e2e/package.json
revert-deps-cypress-update:
	if [ -f  e2e/docker-composetmp.yml ]; then rm e2e/docker-composetmp.yml; fi
	if [ -f  e2e/packagetmp.json ]; then rm e2e/packagetmp.json; fi
	git checkout e2e/docker-compose.yml
	git checkout e2e/package.json
deps-cypress-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/cypressUpdateOne.sh | bash
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-node-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/nodeUpdatesOne.sh | bash
deps-quick-update: deps-cypress-update deps-plugins-update deps-java-update deps-node-update
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash
dc-migration:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/setupDockerCompose.sh | bash
