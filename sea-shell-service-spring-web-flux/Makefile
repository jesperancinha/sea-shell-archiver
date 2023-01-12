build:
	mvn clean install
build-report:
	mvn clean install -Preports jacoco:prepare-agent package jacoco:report
local-pipeline: build build-report
