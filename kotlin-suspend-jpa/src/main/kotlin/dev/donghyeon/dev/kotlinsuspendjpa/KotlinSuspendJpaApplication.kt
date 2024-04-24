package dev.donghyeon.dev.kotlinsuspendjpa

import dev.donghyeon.dev.kotlinsuspendjpa.test.SettlementPurchaseService
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinSuspendJpaApplication(
    private val settlementPurchaseService: SettlementPurchaseService,
) {
    @Bean
    fun commandLineRunner(): CommandLineRunner {
        return CommandLineRunner {
            val create = settlementPurchaseService.create()
            runBlocking { settlementPurchaseService.sendMoney(create) }
        }
    }
}


fun main(args: Array<String>) {
    runApplication<KotlinSuspendJpaApplication>(*args)
}
