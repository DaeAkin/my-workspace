package dev.donghyeon.dev.meetingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MeetingServiceApplication

fun main(args: Array<String>) {
    runApplication<MeetingServiceApplication>(*args)
}
