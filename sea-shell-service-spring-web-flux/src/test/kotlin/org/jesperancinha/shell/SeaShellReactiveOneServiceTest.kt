package org.jesperancinha.shell

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import org.jesperancinha.shell.webflux.service.SeaShellReactiveOneServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import java.time.Duration

@SpringBootTest
class SeaShellReactiveOneServiceTest @Autowired constructor(
    @Autowired
    private val seaShellReactiveOneService: SeaShellReactiveOneServiceImpl
) {

    @Test
    fun `should return a non null list flux of sea shell ids`() {
        seaShellReactiveOneService.allIds().shouldNotBeNull()
    }

    @Test
    fun `should return a non null mono of a sea shell by id`() {
        seaShellReactiveOneService.getSeaShellById(1L).shouldNotBeNull()
    }

    @Test
    fun `should return a non null mono of a person by id`() {
        seaShellReactiveOneService.getPersonById(1L).shouldNotBeNull()
    }

    @Test
    fun `should return a non null mono of a costume by id`() {
        seaShellReactiveOneService.getCostumeById(1L).shouldNotBeNull()
    }

    @Test
    fun `should return a non null mono of an account by id`() {
        seaShellReactiveOneService.getAccountById("ACCOUNTID").shouldNotBeNull()
    }

    @Test
    fun `should return a non null mono of a top by id`() {
        seaShellReactiveOneService.getTopById(1L).shouldNotBeNull()
    }

    @Test
    fun `should return a non null mono of a lower by id`() {
        seaShellReactiveOneService.getLowerById(1L).shouldNotBeNull()
    }
}