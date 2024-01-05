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

## Technologies Used

---

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/docker-50.png "Docker")](https://www.docker.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/docker-compose-50.png "Docker Compose")](https://docs.docker.com/compose/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/java-50.png "Java")](https://www.oracle.com/nl/java/ )
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-50.png "Spring Framework")](https://spring.io/projects/spring-framework)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-boot-50.png "Spring Boot")](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-webflux-50.png "Spring Webfllux")](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-reactor-50.png "Spring Reactor")](https://www.docker.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/bash-50.png "Bash")](https://www.gnu.org/software/bash/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/swagger-50.png "Swagger")](https://swagger.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/cypress-50.png "Cypress")](https://www.cypress.io/)

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

This project is also the official support project of my article on medium:

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/swlh/reactive-programming-applied-to-legacy-services-a-webflux-example-4d1c2ad40bd4) [Reactive Programming applied to Legacy Services — A WebFlux example](https://medium.com/swlh/reactive-programming-applied-to-legacy-services-a-webflux-example-4d1c2ad40bd4)

#### Stable releases

-   [1.0.0](https://github.com/jesperancinha/sea-shell-archiver/tree/1.0.0) - [34ff2b4b760ef28eab1f8243a60d6739bb0d374a](https://github.com/jesperancinha/sea-shell-archiver/tree/1.0.0)
-   [2.0.0](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.0) - [8b8b5ea74daa0fe71d04ba858ce57f9b09d1d959](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.0)
-   [2.0.1](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.1) - [3840b38fd0bd6d89b863a64544d0286f5d4111e3](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.1) - Java / JDK17
-   [2.0.2](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.2) - [77969471de7e36234c26c1e435744f44e4be0690](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.2) - Major refactoring / Java / JDK 17
-   [2.0.3](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.3) - [c04b5c679a7110a1ad898d2670b5e0a9cf7c9cba](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.3) - JDK17 / Kotlin 1.8 / Major services in Kotlin with WebFlux (Flux/Mono)
-   [2.0.4](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.4) - [91a26aa25970f3e0bbc14716e406e5b0ae99926e](https://github.com/jesperancinha/sea-shell-archiver/tree/2.0.4) - Kotlin 1.8 / REST Fixes / Introducing E2E tests

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
docker compose up
```

To terminate your tests, you can just run 

```shell
docker compose down
```

## Java version

```bash
sdk install java 16.0.1.hs-adpt
sdk use java 16.0.1.hs-adpt
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
-   [NLJUG Reactive Programming](https://nljug.org/java-magazine/reactive-programming/)
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

### Domain knowledge

-   [Girls like you by Maroon 5 featuring Cardi B Lyrics](https://songteksten.net/lyric/2881/100824/maroon-5/girls-like-you-ft-cardi-b.html)
-   [British seashell guide: how to identify and where to find](https://www.countryfile.com/wildlife/marine-life/british-seashell-guide-how-to-identify-and-where-to-find/)
-   [Game of Thrones Official by HBO](https://www.hbo.com/game-of-thrones)

### Legacy

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

<div align="center">

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-100/JEOrgLogo-27.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![](https://img.shields.io/badge/youtube-%230077B5.svg?style=for-the-badge&logo=youtube&color=FF0000)](https://www.youtube.com/channel/UCzS_JK7QsZ7ZH-zTc5kBX_g)
[![](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/@jofisaes)
[![](https://img.shields.io/badge/Buy%20Me%20A%20Coffee-%230077B5.svg?style=for-the-badge&logo=buymeacoffee&color=yellow)](https://www.buymeacoffee.com/jesperancinha)
[![](https://img.shields.io/badge/Twitter-%230077B5.svg?style=for-the-badge&logo=twitter&color=white)](https://twitter.com/joaofse)
[![](https://img.shields.io/badge/Mastodon-%230077B5.svg?style=for-the-badge&logo=mastodon&color=afd7f7)](https://masto.ai/@jesperancinha)
[![](https://img.shields.io/badge/Facebook-%230077B5.svg?style=for-the-badge&logo=facebook&color=3b5998)](https://www.facebook.com/joaofisaes/)
[![](https://img.shields.io/badge/Sessionize-%230077B5.svg?style=for-the-badge&logo=sessionize&color=cffff6)](https://sessionize.com/joao-esperancinha)
[![](https://img.shields.io/badge/Instagram-%230077B5.svg?style=for-the-badge&logo=instagram&color=purple)](https://www.instagram.com/joaofisaes)
[![](https://img.shields.io/badge/Tumblr-%230077B5.svg?style=for-the-badge&logo=tumblr&color=192841)](https://jofisaes.tumblr.com)
[![](https://img.shields.io/badge/Spotify-1ED760?style=for-the-badge&logo=spotify&logoColor=white)](https://open.spotify.com/user/jlnozkcomrxgsaip7yvffpqqm)
[![](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/joaoesperancinha/)
[![](https://img.shields.io/badge/Xing-%230077B5.svg?style=for-the-badge&logo=xing&color=064e40)](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![](https://img.shields.io/badge/YCombinator-%230077B5.svg?style=for-the-badge&logo=ycombinator&color=d0d9cd)](https://news.ycombinator.com/user?id=jesperancinha)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
[![](https://img.shields.io/badge/bitbucket-%230077B5.svg?style=for-the-badge&logo=bitbucket&color=blue)](https://bitbucket.org/jesperancinha)
[![](https://img.shields.io/badge/gitlab-%230077B5.svg?style=for-the-badge&logo=gitlab&color=orange)](https://gitlab.com/jesperancinha)
[![](https://img.shields.io/badge/Sonatype%20Search%20Repos-%230077B5.svg?style=for-the-badge&color=red)](https://central.sonatype.com/search?smo=true&q=org.jesperancinha)
[![](https://img.shields.io/badge/Stack%20Overflow-%230077B5.svg?style=for-the-badge&logo=stackoverflow&color=5A5A5A)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![](https://img.shields.io/badge/Credly-%230077B5.svg?style=for-the-badge&logo=credly&color=064e40)](https://www.credly.com/users/joao-esperancinha)
[![](https://img.shields.io/badge/Coursera-%230077B5.svg?style=for-the-badge&logo=coursera&color=000080)](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![](https://img.shields.io/badge/Docker-%230077B5.svg?style=for-the-badge&logo=docker&color=cyan)](https://hub.docker.com/u/jesperancinha)
[![](https://img.shields.io/badge/Reddit-%230077B5.svg?style=for-the-badge&logo=reddit&color=black)](https://www.reddit.com/user/jesperancinha/)
[![](https://img.shields.io/badge/Hackernoon-%230077B5.svg?style=for-the-badge&logo=hackernoon&color=0a5d00)](https://hackernoon.com/@jesperancinha)
[![](https://img.shields.io/badge/Dev.TO-%230077B5.svg?style=for-the-badge&color=black&logo=dev.to)](https://dev.to/jofisaes)
[![](https://img.shields.io/badge/Code%20Project-%230077B5.svg?style=for-the-badge&logo=codeproject&color=063b00)](https://www.codeproject.com/Members/jesperancinha)
[![](https://img.shields.io/badge/Free%20Code%20Camp-%230077B5.svg?style=for-the-badge&logo=freecodecamp&color=0a5d00)](https://www.freecodecamp.org/jofisaes)
[![](https://img.shields.io/badge/Hackerrank-%230077B5.svg?style=for-the-badge&logo=hackerrank&color=1e2f97)](https://www.hackerrank.com/jofisaes)
[![](https://img.shields.io/badge/LeetCode-%230077B5.svg?style=for-the-badge&logo=leetcode&color=002647)](https://leetcode.com/jofisaes)
[![](https://img.shields.io/badge/Codewars-%230077B5.svg?style=for-the-badge&logo=codewars&color=722F37)](https://www.codewars.com/users/jesperancinha)
[![](https://img.shields.io/badge/CodePen-%230077B5.svg?style=for-the-badge&logo=codepen&color=black)](https://codepen.io/jesperancinha)
[![](https://img.shields.io/badge/HackerEarth-%230077B5.svg?style=for-the-badge&logo=hackerearth&color=00035b)](https://www.hackerearth.com/@jofisaes)
[![](https://img.shields.io/badge/Khan%20Academy-%230077B5.svg?style=for-the-badge&logo=khanacademy&color=00035b)](https://www.khanacademy.org/profile/jofisaes)
[![](https://img.shields.io/badge/Pinterest-%230077B5.svg?style=for-the-badge&logo=pinterest&color=FF0000)](https://nl.pinterest.com/jesperancinha)
[![](https://img.shields.io/badge/Quora-%230077B5.svg?style=for-the-badge&logo=quora&color=FF0000)](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
[![](https://img.shields.io/badge/Google%20Play-%230077B5.svg?style=for-the-badge&logo=googleplay&color=purple)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![](https://img.shields.io/badge/Coderbyte-%230077B5.svg?style=for-the-badge&color=blue&logo=coderbyte)](https://coderbyte.com/profile/jesperancinha)
[![](https://img.shields.io/badge/InfoQ-%230077B5.svg?style=for-the-badge&color=blue&logo=coderbyte)](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![](https://img.shields.io/badge/OCP%20Java%2011-%230077B5.svg?style=for-the-badge&logo=oracle&color=064e40)](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![](https://img.shields.io/badge/OCP%20JEE%207-%230077B5.svg?style=for-the-badge&logo=oracle&color=064e40)](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![](https://img.shields.io/badge/VMWare%20Spring%20Professional%202021-%230077B5.svg?style=for-the-badge&logo=spring&color=064e40)](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
[![](https://img.shields.io/badge/IBM%20Cybersecurity%20Analyst%20Professional-%230077B5.svg?style=for-the-badge&logo=ibm&color=064e40)](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
[![](https://img.shields.io/badge/Deep%20Learning-%230077B5.svg?style=for-the-badge&logo=ibm&color=064e40)](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
[![](https://img.shields.io/badge/Certified%20Neo4j%20Professional-%230077B5.svg?style=for-the-badge&logo=neo4j&color=064e40)](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
[![](https://img.shields.io/badge/Certified%20Advanced%20JavaScript%20Developer-%230077B5.svg?style=for-the-badge&logo=javascript&color=064e40)](https://cancanit.com/certified/1462/)
[![](https://img.shields.io/badge/Kong%20Champions-%230077B5.svg?style=for-the-badge&logo=kong&color=064e40)](https://konghq.com/kong-champions)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=064e40&style=for-the-badge "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=064e40&style=for-the-badge "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=orange&style=for-the-badge "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)

</div>
