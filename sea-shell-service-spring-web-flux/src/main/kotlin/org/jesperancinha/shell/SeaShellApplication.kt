package org.jesperancinha.shell

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication(exclude = [WebMvcAutoConfiguration::class, ErrorMvcAutoConfiguration::class])
object SeaShellApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(SeaShellApplication::class.java, *args)
    }
}