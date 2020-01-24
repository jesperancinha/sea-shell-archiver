# sea-shell-archiver
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Sea%20Shell%20Archiver&color=informational)](https://github.com/jesperancinha/sea-shell-archiver) 
[![GitHub release](https://img.shields.io/github/release-pre/jesperancinha/sea-shell-archiver.svg)](#)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/89cc4b270cda4a448ce4fa895b30ec55)](https://www.codacy.com/manual/jofisaes/sea-shell-archiver?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/sea-shell-archiver&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/c71192de-5569-4741-aaa9-503217514483)](https://codebeat.co/projects/github-com-jesperancinha-sea-shell-archiver-master)
[![CircleCI](https://circleci.com/gh/jesperancinha/sea-shell-archiver.svg?style=svg)](https://circleci.com/gh/jesperancinha/sea-shell-archiver)
[![Build Status](https://travis-ci.org/jesperancinha/sea-shell-archiver.svg?branch=master)](https://travis-ci.org/jesperancinha/sea-shell-archiver)
[![BCH compliance](https://bettercodehub.com/edge/badge/jesperancinha/sea-shell-archiver?branch=master)](https://bettercodehub.com/)
[![Build status](https://ci.appveyor.com/api/projects/status/eka55ffpbjkxq55p?svg=true)](https://ci.appveyor.com/project/jesperancinha/sea-shell-archiver)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/sea-shell-archiver/badge.svg)](https://snyk.io/test/github/jesperancinha/sea-shell-archiver)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/sea-shell-archiver.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/sea-shell-archiver.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/sea-shell-archiver.svg)](#)

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

-   Unfortunately [Blockhound](https://github.com/reactor/BlockHound) doesn't seem to work well with Java 13. This is why I had to switch back to 12.
-   To run circleCI I had to use an external [Docker image](https://hub.docker.com/r/codeaches/openjdk) from [codecaches](https://hub.docker.com/u/codeaches).

Topics discussed:

-   [WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html)
    -   [Flux](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)
    -   [Mono](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html)

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
-   [Baeldung Spring WebFlux](https://www.baeldung.com/spring-webflux)
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

### Tools

-   [Syntax Highlight Code In Word Documents](http://www.planetb.ca/syntax-highlight-word)

## About me

[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social)](https://twitter.com/joaofse)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=jesperancinha&style=social)](https://github.com/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=joaofilipesabinoesperancinha.nl&color=6495ED)](http://joaofilipesabinoesperancinha.nl)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Time%20Disruption%20Studios&color=6495ED)](http://tds.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=Image%20Train%20Filters&color=6495ED)](http://itf.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Homepage&message=MancalaJE&color=6495ED)](http://mancalaje.joaofilipesabinoesperancinha.nl/)
[![Generic badge](https://img.shields.io/static/v1.svg?label=DEV&message=Profile&color=green)](https://dev.to/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Medium&message=@jofisaes&color=green)](https://medium.com/@jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackernoon&message=@jesperancinha&color=green)](https://hackernoon.com/@jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Free%20Code%20Camp&message=jofisaes&color=008000)](https://www.freecodecamp.org/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Hackerrank&message=jofisaes&color=008000)](https://www.hackerrank.com/jofisaes)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Forces&message=jesperancinha&color=008000)](https://codeforces.com/profile/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Coder%20Byte&message=jesperancinha&color=008000)](https://coderbyte.com/profile/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Wars&message=jesperancinha&color=008000)](https://www.codewars.com/users/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Acclaim%20Badges&message=joao-esperancinha&color=red)](https://www.youracclaim.com/users/joao-esperancinha/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Badges.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Status.md)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Google%20Apps&message=Joao+Filipe+Sabino+Esperancinha&color=orange)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Code%20Pen&message=jesperancinha&color=orange)](https://codepen.io/jesperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Android&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-android)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20Java&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate-modules/tree/master/itf-chartizate-java)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=ITF%20Chartizate%20API&color=yellow)](https://github.com/JEsperancinhaOrg/itf-chartizate/tree/master/itf-chartizate-api)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Core&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-core)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Markdowner%20Filter&color=yellow)](https://github.com/jesperancinha/markdowner/tree/master/markdowner-filter)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Docker%20Images&message=jesperanciha&color=099CEC)](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/DockerImages.md)
