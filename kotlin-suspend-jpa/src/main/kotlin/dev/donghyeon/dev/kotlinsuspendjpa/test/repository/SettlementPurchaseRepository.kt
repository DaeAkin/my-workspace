package dev.donghyeon.dev.kotlinsuspendjpa.test.repository

import dev.donghyeon.dev.kotlinsuspendjpa.test.SettlementPurchase
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementPurchaseRepository: JpaRepository<SettlementPurchase, Long> {
}
