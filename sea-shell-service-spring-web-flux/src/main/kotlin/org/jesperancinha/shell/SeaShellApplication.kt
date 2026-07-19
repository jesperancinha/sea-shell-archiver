package org.jesperancinha.shell

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.webmvc.autoconfigure.WebMvcAutoConfiguration
import org.springframework.boot.webmvc.autoconfigure.error.ErrorMvcAutoConfiguration
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication(exclude = [WebMvcAutoConfiguration::class, ErrorMvcAutoConfiguration::class])
class SeaShellApplication {

}
fun main(args: Array<String>) {
    SpringApplication.run(SeaShellApplication::class.java, *args)
}