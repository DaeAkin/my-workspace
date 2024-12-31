package dev.donghyeon.dev.fluentbittest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FluentBitTestApplication

fun main(args: Array<String>) {
    runApplication<FluentBitTestApplication>(*args)
}
