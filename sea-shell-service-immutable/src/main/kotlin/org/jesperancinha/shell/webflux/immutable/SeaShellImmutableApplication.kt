package org.jesperancinha.shell.webflux.immutable

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration

@SpringBootApplication(exclude = [WebMvcAutoConfiguration::class, ErrorMvcAutoConfiguration::class])
class SeaShellImmutableApplication

fun main(args: Array<String>) {
    SpringApplication.run(SeaShellImmutableApplication::class.java, *args)
}