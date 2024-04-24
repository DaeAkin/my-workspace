package dev.donghyeon.dev.kotlinsuspendjpa.test

import jakarta.persistence.*

@Entity
class LogRemittance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

) {
    companion object {
        fun success(settlementPurchase: SettlementPurchase) {
            val logRemittance = LogRemittance()
            settlementPurchase.addLogRemittance(logRemittance)
        }
    }
    @ManyToOne
    var settlementPurchase: SettlementPurchase? = null
}
