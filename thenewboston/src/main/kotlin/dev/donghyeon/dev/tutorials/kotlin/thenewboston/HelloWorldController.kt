package dev.donghyeon.dev.tutorials.kotlin.thenewboston

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloWorldController {
    
    @GetMapping
    fun helloWorld(): String = "Hello, this is a Rest endpoint!"
}
