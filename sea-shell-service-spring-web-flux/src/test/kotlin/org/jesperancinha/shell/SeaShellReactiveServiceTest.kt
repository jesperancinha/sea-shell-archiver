package org.jesperancinha.shell

import io.kotest.matchers.nulls.shouldNotBeNull
import org.jesperancinha.shell.webflux.service.SeaShellReactiveService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SeaShellReactiveServiceTest @Autowired constructor(
    private val seaShellReactiveService: SeaShellReactiveService
) {

    @Test
    fun `should return a non null flux of shells`() {
        seaShellReactiveService.allSeaShells().shouldNotBeNull()
    }
}