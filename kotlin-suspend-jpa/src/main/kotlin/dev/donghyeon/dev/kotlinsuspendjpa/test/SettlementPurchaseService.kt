package dev.donghyeon.dev.kotlinsuspendjpa.test

import dev.donghyeon.dev.kotlinsuspendjpa.test.repository.SettlementPurchaseRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class SettlementPurchaseService(
    private val remittanceService: RemittanceService,
    private val settlementPurchaseRepository: SettlementPurchaseRepository
) {

    @Transactional
    suspend fun sendMoney(settlementPurchaseId: Long) {
        // 전처리 검증

        // 돈보내기
        remittanceService.remittance(settlementPurchaseId)

        // 돈보내고 후처리
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun create(): Long {
        return settlementPurchaseRepository.save(
            SettlementPurchase()
        ).id!!
    }
}
