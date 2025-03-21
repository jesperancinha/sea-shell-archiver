# sea-shell-archiver

---
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Sea%20Shell%20Archiver&color=informational)](https://github.com/jesperancinha/sea-shell-archiver)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/sea-shell-archiver.svg)](#)
[![GitHub contributors](https://img.shields.io/github/contributors/jesperancinha/sea-shell-archiver.svg)](https://github.com/jesperancinha/sea-shell-archiver)

[![CircleCI](https://circleci.com/gh/jesperancinha/sea-shell-archiver.svg?style=svg)](https://circleci.com/gh/jesperancinha/sea-shell-archiver)
[![Build status](https://ci.appveyor.com/api/projects/status/eka55ffpbjkxq55p?svg=true)](https://ci.appveyor.com/project/jesperancinha/sea-shell-archiver)
[![sea-shell-archiver](https://github.com/jesperancinha/sea-shell-archiver/actions/workflows/sea-shell-archiver.yml/badge.svg)](https://github.com/jesperancinha/sea-shell-archiver/actions/workflows/sea-shell-archiver.yml)
[![S_ARCH e2e-sea-shell-archiver](https://github.com/jesperancinha/sea-shell-archiver/actions/workflows/sea-shell-archiver-e2e.yml/badge.svg)](https://github.com/jesperancinha/sea-shell-archiver/actions/workflows/sea-shell-archiver-e2e.yml)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/89cc4b270cda4a448ce4fa895b30ec55)](https://www.codacy.com/manual/jofisaes/sea-shell-archiver?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/sea-shell-archiver&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/c71192de-5569-4741-aaa9-503217514483)](https://codebeat.co/projects/github-com-jesperancinha-sea-shell-archiver-master)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/sea-shell-archiver/badge.svg)](https://snyk.io/test/github/jesperancinha/sea-shell-archiver)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/89cc4b270cda4a448ce4fa895b30ec55)](https://www.codacy.com/gh/jesperancinha/sea-shell-archiver/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/sea-shell-archiver&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/sea-shell-archiver/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/sea-shell-archiver?branch=master)
[![codecov](https://codecov.io/gh/jesperancinha/sea-shell-archiver/branch/master/graph/badge.svg?token=G8jNMRYmyp)](https://codecov.io/gh/jesperancinha/sea-shell-archiver)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/sea-shell-archiver.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/sea-shell-archiver.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/sea-shell-archiver.svg)](#)

---

## Description

This project is a Sea Shell archiver for an imaginary world.

This is the description of that world:

>Imagine a world were beings live in gigantic sea shells. Sea shells are their home. Inside you can find one or more people. You can also find one or more costumes with different configuration. The value of the shell is evaluated by the sum of the values of all the individual costumes.
>Every shell has a slogan.
>
>For the purposes of this example I decided to use [Cardi B](https://www.cardibofficial.com/)'s [rap solo](https://songteksten.net/lyric/2881/100824/maroon-5/girls-like-you-ft-cardi-b.html) in "Girls like you" by [Maroon 5](https://www.maroon5.com/) to establish slogans.
>As persons living inside shells I chose [Game of Thrones](https://www.hbo.com/game-of-thrones) charachters.
>For the rest, just my vivid imagination :).
>
>This application is very old. Its design still is base on the paradigms of the time. It's implemented in SOAP. Furthermore, every single entity has a dedicated read service associated with it. As a result, every time you need to get the data from a particular sea shell, you have to make multiple requests.
>This situation warrants a solution.
>Times have changed and applications need to move faster and be scalable, but due to business restrictions I cannot change my SOAP services.
>A solution could be using Reactive Streams in the context of a Reactive architecture.

This is what this application is about.

Essentially this is a fun exercise to help understand some of the mechanisms behind the concepts described in the [Reactive Manifesto](https://www.reactivemanifesto.org/).

Topics discussed:

-   [WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html)
	-   [Flux](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)
	-   [Mono](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html)

<details>
<summary><b>Stable Releases</b></summary>

#### Stable releases

-   [1.0.0](https://github.com/jesperancinha/sea-shell-archiver/tree/1.0.0) - [34ff2b4b760ef28eab1f8243a60d6739bb0d374a](https://github.com/jesperancinha/sea-shell-archiver/tree/1.0.0)
-   [2.0.0](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.0) - [8b8b5ea74daa0fe71d04ba858ce57f9b09d1d959](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.0)
-   [2.0.1](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.1) - [3840b38fd0bd6d89b863a64544d0286f5d4111e3](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.1) - Java / JDK17
-   [2.0.2](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.2) - [77969471de7e36234c26c1e435744f44e4be0690](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.2) - Major refactoring / Java / JDK 17
-   [2.0.3](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.3) - [c04b5c679a7110a1ad898d2670b5e0a9cf7c9cba](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.3) - JDK17 / Kotlin 1.8 / Major services in Kotlin with WebFlux (Flux/Mono)
-   [2.0.4](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.4) - [91a26aa25970f3e0bbc14716e406e5b0ae99926e](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.4) - Kotlin 1.8 / REST Fixes / Introducing E2E tests

</details>

## How to Guide

Revision dates: 2020/01/29, 

To start interacting with it, you need to start runnable modules:

1.  [sea-shell-soap-service](./sea-shell-soap-wiremock/sea-shell-soap-service) - [SeaShellWiremockSoapLauncher](sea-shell-soap-wiremock/sea-shell-soap-service/src/main/java/org/jesperancinha/shell/SeaShellWiremockSoapLauncher.java) - Soap mock service (The blocking source) - Runs on port 8090 - (DEPRECATED) - There will be a major mock update on a coming version sometime.
2.  [sea-shell-rest-service](./sea-shell-service-spring-web-flux/sea-shell-rest-service) - [SeaShellApplication](sea-shell-service-spring-web-flux/sea-shell-rest-service/src/main/java/org/jesperancinha/shell/webflux/SeaShellApplication.java) - WireMock Rest Service data provider (Uses the SOAP blocking, legacy, outdated SOAP service) - Runs on port 8080
3.  [sea-shell-client](./sea-shell-client) - Three executables to be used in running against [sea-shell-rest-service](./sea-shell-service-spring-web-flux/sea-shell-rest-service) using futures and fork joins. Be sure to run the REST service and the WireMock SOAP service
4.  [sea-shell-sea-shell-service-immutable](./sea-shell-service-immutable) - Standalone compact service running in an immutable fashion using [Java records](https://openjdk.java.net/jeps/359) - Runs on port 8081

In order to run the full example, please create your local bin folder

```bash
make build local
```

This will create the bin folder. The jars are self-explanatory. To run each one of them please use the corresponding command in different terminal windows:

```shell
java -jar sea-shell-soap-service.jar
java -jar sea-shell-rest-service.jar
java -jar sea-shell-service-immutable.jar
```

You can, alternatively, with your docker machine/desktop running, run a docker container:

```shell
docker-compose up
```

To terminate your tests, you can just run 

```shell
docker-compose down
```

## Java version

```bash
sdk install java 19-open
sdk use java $(sdk list java | grep installed | grep "| 19" |  cut -d'|' -f6- | cut -d' ' -f2-)
```

## Testing endpoints


### Blocking and Almost Reactive Methods
1.  [http://localhost:8080/seashells](http://localhost:8080/seashells)
2.  http://localhost:8080/seashells/1
3.  [http://localhost:8080/seashells/slogans](http://localhost:8080/seashells/slogans)
4.  http://localhost:8080/seashells/block
5.  http://localhost:8080/seashells/block/1
6.  http://localhost:8080/seashells/reactiveblock
7.  http://localhost:8080/seashells/reactiveWithDelay
8.  [http://localhost:8080/seashells/reactiveWithForkJoins](http://localhost:8080/seashells/reactiveWithForkJoins)

### Reactive endpoints
1.  http://localhost:8080/seashells/reactive/1
2.  http://localhost:8080/seashells/reactive/rootCostume/1/1
3.  http://localhost:8080/seashells/reactive/rootShell/1/1
4.  http://localhost:8080/seashells/reactive/rootCostumeSlowTop/1/1
5.  [http://localhost:8080/seashells/reactive/rootCostumeSlowLower/1/1](http://localhost:8080/seashells/reactive/rootCostumeSlowLower/1/1)

### Reactive One endpoints
1.  http://localhost:8080/seashells/one
2.  http://localhost:8080/seashells/one/1
3.  http://localhost:8080/seashells/one/person/1
4.  http://localhost:8080/seashells/one/costume/1
5.  http://localhost:8080/seashells/one/account/1
6.  http://localhost:8080/seashells/one/top/1
7.  http://localhost:8080/seashells/one/lower/1

### Immutable Reactive endpoints (the real deal!)
1.  http://localhost:8081/seashells/immutable
2.  [http://localhost:8081/seashells/immutable/1](http://localhost:8081/seashells/immutable/1)
3.  [http://localhost:8081/seashells/immutable/person/1](http://localhost:8081/seashells/immutable/person/1)
4.  [http://localhost:8081/seashells/immutable/costume/1](http://localhost:8081/seashells/immutable/costume/1)
5.  http://localhost:8081/seashells/immutable/account/1
6.  http://localhost:8081/seashells/immutable/top/1
7.  http://localhost:8081/seashells/immutable/lower/1

## References

Please check the [docs](https://jesperancinha.github.io/sea-shell-archiver/) for more info.

### Technical

-   [Java Records tortured with Lombok yet again (builder edition)](https://softwaregarden.dev/en/posts/new-java/records/vs-lombok-yet-again-with-builder-pattern/)
-   [Why does ForkJoinPool::invoke() block the main thread?](https://stackoverflow.com/questions/52591776/why-does-forkjoinpoolinvoke-block-the-main-thread)
-   [Flight of the Flux 1 - Assembly vs Subscription](https://spring.io/blog/2019/03/06/flight-of-the-flux-1-assembly-vs-subscription)
-   [Hanselminutes Podcast 198 - Reactive Extensions for .NET (Rx) with Erik Meijer](https://www.hanselman.com/blog/HanselminutesPodcast198ReactiveExtensionsForNETRxWithErikMeijer.aspx)
-   [Reactive Extensions](https://docs.microsoft.com/en-us/previous-versions/dotnet/reactive-extensions/hh242985(v=vs.103)?redirectedfrom=MSDN)
-   [Reactive Programming by Venkat Subramaniam](https://www.youtube.com/watch?v=weWSYIUdX6c)
-   [The Essence of Reactive Programming - TU Delft Repositories](https://repository.tudelft.nl/islandora/object/uuid:bd900036-40f4-432d-bfab-425cdebc466e/datastream/OBJ/download)
-   [Notes on Reactive Programming Part I: The Reactive Landscape by Dave Syer](https://dzone.com/articles/notes-on-reactive-programming-part-i-the-reactive)
-   [The essence of reactive programming in Java by Uladzimir Sinkevich](https://www.scnsoft.com/blog/java-reactive-programming)
-   [Don't be Homer Simpson with your reactor by Sergei Egorov](https://www.slideshare.net/Pivotal/dont-be-homer-simpson-with-your-reactor)
-   [Avoid Reactor Meltdown by Phil Clay on YouTube](https://www.youtube.com/watch?v=xCu73WVg8Ps)
-   [Avoiding Reactor Meltdown by Phil Clay](https://github.com/philsttr/avoiding-reactor-meltdown)
-   [JsonView Chrome Plugin](https://chrome.google.com/webstore/detail/jsonview/chklaanhfefbnpoihckbnefhakgolnmc)
-   [Spring WebFlux Tutorial](https://howtodoinjava.com/spring-webflux/spring-webflux-tutorial/)
-   [Wiremock Running as a Standalone Process](http://wiremock.org/docs/running-standalone/)
-   [Move your apps to the cloud in weeks](https://pivotal.io/application-modernization)
-   [The Twelve Factors](https://12factor.net/)
-   [DDD and Microservices: Like Peanut Butter and Jelly - Matt Stine](https://content.pivotal.io/slides/ddd-and-microservices-like-peanut-butter-and-jelly-matt-stine)
-   [Reactive Streams](http://www.reactive-streams.org/)
-   [Reactive Manifesto](https://www.reactivemanifesto.org/)
-   [Hands on Reactive Programming in Spring](https://www.bol.com/nl/p/hands-on-reactive-programming-in-spring-5/9200000084600333/?bltgh=pCsqVROC1Zv4I9xR0JRJfw.1.4.ProductTitle)
-   [QPI Architecture](https://en.wikipedia.org/wiki/Intel_QuickPath_Interconnect)
-   [ITNext How to make legacy code reactive](https://itnext.io/how-to-make-legacy-code-reactive-2debcb3d0a40)
-   [Spring 5 Reactive Security Example](https://github.com/eugenp/tutorials/tree/master/spring-5-reactive-security)
-   [Block Hound](https://github.com/reactor/BlockHound)
-   [Lombok Tips And Tricks](https://github.com/piczmar/lombok-tips-and-tricks)
-   [In spring boot webflux based microservice, who is the subscriber?](https://stackoverflow.com/questions/48181801/in-spring-boot-webflux-based-microservice-who-is-the-subscriber)
-   [How REST endpoints are auto subscribed while calling from Browser/REST Client?](https://stackoverflow.com/questions/50795071/how-rest-endpoints-are-auto-subscribed-while-calling-from-browser-rest-client)
-   [Reactive Programming with Node.js](https://www.amazon.com/Reactive-Programming-Node-js-Fernando-Doglio/dp/1484221516)
-   [Spring Data R2DBC](https://spring.io/projects/spring-data-r2dbc)
-   [Exception occurred during code generation for the WSDL : java.lang.NoClassDefFoundError:](https://stackoverflow.com/questions/35797731/exception-occurred-during-code-generation-for-the-wsdl-java-lang-noclassdeffou)
-   [Apache Axis2 1.6.4 Release Note](https://axis.apache.org/axis2/java/core/release-notes/1.6.4.html)
-   [Index of /dist/axis/axis2/java/core/1.6.4](https://archive.apache.org/dist/axis/axis2/java/core/1.6.4/)
-   [AXIS2 JAVA.LANG.NOCLASSDEFFOUNDERROR: ORG/APACHE/WS/COMMONS/SCHEMA/UTILS/NAMESPACEPREFIXLIST](https://yiingw.wordpress.com/2018/02/12/axis2-java-lang-noclassdeffounderror-org-apache-ws-commons-schema-utils-namespaceprefixlist/)
-   [Upgrade to JDK12?](https://discuss.circleci.com/t/upgrade-to-jdk12/29566/7)

### Hints and Tricks

Please check my documentation on [Hints&Tricks](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Hints%26Tricks.md) for more details

If for any reason, the import of this project fails or the upgrade fails, please close your IDE.	Via a shell run the following command:

```bash
git clean -xdf
```

This will remove all non-versioned files from your repo. It will allow your IDE to re-import fresh from start. Make sure that the caches are invalidated and removed in this case.

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
