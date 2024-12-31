package dev.donghyeon.dev.fluentbittest.api

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestApi {

    private val logger  = LoggerFactory.getLogger(TestApi::class.java)

    @RequestMapping("/test")
    fun test(): String {
        logger.info("test")

        return "Hello World"
    }
}
