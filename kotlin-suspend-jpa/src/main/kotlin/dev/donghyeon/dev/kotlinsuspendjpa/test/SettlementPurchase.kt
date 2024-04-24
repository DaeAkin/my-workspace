package dev.donghyeon.dev.kotlinsuspendjpa.test

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class SettlementPurchase(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var remittanceCompletedAt: LocalDateTime? = null,
) {
    @OneToMany(mappedBy = "settlementPurchase")
    val logRemittances: MutableList<LogRemittance> = mutableListOf()

    fun addLogRemittance(logRemittance: LogRemittance) {
        logRemittances.add(logRemittance)
        logRemittance.settlementPurchase = this
    }
}
