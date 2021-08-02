# sea-shell-archiver

---
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Sea%20Shell%20Archiver&color=informational)](https://github.com/jesperancinha/sea-shell-archiver)
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/sea-shell-archiver.svg)](#)

[![CircleCI](https://circleci.com/gh/jesperancinha/sea-shell-archiver.svg?style=svg)](https://circleci.com/gh/jesperancinha/sea-shell-archiver)
[![Build status](https://ci.appveyor.com/api/projects/status/eka55ffpbjkxq55p?svg=true)](https://ci.appveyor.com/project/jesperancinha/sea-shell-archiver)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/89cc4b270cda4a448ce4fa895b30ec55)](https://www.codacy.com/manual/jofisaes/sea-shell-archiver?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/sea-shell-archiver&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/c71192de-5569-4741-aaa9-503217514483)](https://codebeat.co/projects/github-com-jesperancinha-sea-shell-archiver-master)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/sea-shell-archiver?branch=master)](https://bettercodehub.com/)
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

Essentially this is a fun exercise to help understand all the mechanisms behind the concepts described in the [Reactive Manifesto](https://www.reactivemanifesto.org/).

NOTES:

-   To run circleCI I had to use an external [Docker image](https://hub.docker.com/r/codeaches/openjdk) from [codecaches](https://hub.docker.com/u/codeaches).

Topics discussed:

-   [WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html)
    -   [Flux](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)
    -   [Mono](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html)

This project is also the official support project of my article on medium:

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/swlh/reactive-programming-applied-to-legacy-services-a-webflux-example-4d1c2ad40bd4) [Reactive Programming applied to Legacy Services — A WebFlux example](https://medium.com/swlh/reactive-programming-applied-to-legacy-services-a-webflux-example-4d1c2ad40bd4)

## Review notes - Roadmap to 2.0.0 - Expected release date - Coming soon

This project is currently under review.
Problems to fix include:

- Maintainability - JAXB current configuration as it stands is incompatible with JDK updates
- Windows Compatibility - Though not originally a point of focus, it will be a issue to sort out for the next version

Please check review logs on [Review Logs](./ReviewLogs.md).

## Java version

```bash
sdk install java 16.0.1.hs-adpt
sdk use java 16.0.1.hs-adpt
```

## References

### Technical

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

If for any reason, the import of this project fails or the upgrade fails, please close your IDE.
Via a shell run the following command:

```bash
git clean -xdf
```

This will remove all non-versioned files from your repo. It will allow your IDE to re-import fresh from start. Make sure that the caches are invalidated and removed in this case.


## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/credly-20.png "Credly")](https://www.credly.com/users/joao-esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=6495ED "João Esperancinha Homepage")](https://joaofilipesabinoesperancinha.nl/)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)   
[![Generic badge](https://img.shields.io/static/v1.svg?label=Articles&message=Across%20The%20Web&color=purple)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Articles.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Webapp&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)   
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/github-20.png "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bintray-20.png "BinTray")](https://bintray.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeforces-20.png "Code Forces")](https://codeforces.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)

## Achievements

[![VMware Spring Professional 2021](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/vmware-spring-professional-2021.png "VMware Spring Professional 2021")](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
[![Oracle Certified Professional, JEE 7 Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-ee-7-application-developer-100.png "Oracle Certified Professional, JEE7 Developer")](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![Oracle Certified Professional, Java SE 11 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-11-developer-100.png "Oracle Certified Professional, Java SE 11 Programmer")](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![IBM Cybersecurity Analyst Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/ibm-cybersecurity-analyst-professional-certificate-100.png "IBM Cybersecurity Analyst Professional")](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
[![Certified Advanced JavaScript Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/cancanit-badge-1462-100.png "Certified Advanced JavaScript Developer")](https://cancanit.com/certified/1462/)
[![Certified Neo4j Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/professional_neo4j_developer-100.png "Certified Neo4j Professional")](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
[![Deep Learning](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/deep-learning-100.png "Deep Learning")](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
