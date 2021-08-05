# Sea Shell Archiver Review Logs

##  Roadmap to v3.0.0

() - Kotest
() - Mockk
() - Java Docs
() - Use var wherever possible

##  Roadmap to v2.0.0

### Review notes - Roadmap to 2.0.0 - Expected release date - Coming soon

This project is currently under review.	Problems to fix include:

- Maintainability - JAXB current configuration as it stands is incompatible with JDK updates
- Windows Compatibility - Though not originally a point of focus, it will be a issue to sort out for the next version

Please check review logs on [Review Logs](./ReviewLogs.md).

### Change Logs

2021/08/05 - Article upgrade

2021/08/04 - Records
- Usage of Records
- JDK16 Record usage
- Check Windows Compatibility - [Error on windows #14](https://github.com/jesperancinha/sea-shell-archiver/issues/14)
- Create Java Environment windows instructions and installation script
- Create instructions to run `SeaShellWiremockSoapLauncher`
- Create instructions to run `SeaShellApplication`
- Create instructions to run `SeaShellClient` different runs

2021/08/03 - Migration to Java 16 and Spring style JAXB
- New generated clients on build
- Simpler clients

2021/08/01 - Review started
- Java update to JDK 16
- Lombok upgraded to 1.18.20
- Version check

##  Roadmap to v1.0.0

2020/01/29 - Release 1.0.0

## References

- [com.sun.xml.internal.ws.spi.ProviderImpl Issue](https://github.com/eclipse-ee4j/jax-ws-api/issues/90)
- [Consuming a SOAP web service](https://spring.io/guides/gs/consuming-web-service/)
- [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)