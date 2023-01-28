package dev.donghyeon.dev.springbatch

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.Arrays

@SpringBootApplication
class SpringBatchApplication

fun main(args: Array<String>) {
    System.exit(SpringApplication.exit(SpringApplication.run(arrayOf(SpringBatchApplication::class.java), args)))
}
