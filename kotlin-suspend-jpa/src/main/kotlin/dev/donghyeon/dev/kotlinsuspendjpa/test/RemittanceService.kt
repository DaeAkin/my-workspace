package dev.donghyeon.dev.kotlinsuspendjpa.test

import dev.donghyeon.dev.kotlinsuspendjpa.test.repository.SettlementPurchaseRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.time.LocalDateTime

@Service
class RemittanceService(
    private val settlementPurchaseRepository: SettlementPurchaseRepository,
    private val webClient: WebClient
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    suspend fun remittance(settlementPurchaseId: Long) {
        val settlementPurchase = settlementPurchaseRepository.findById(settlementPurchaseId).get()

        sendMoney()
        settlementPurchase.remittanceCompletedAt = LocalDateTime.now()
        //돈 잘 보냈다고 가정하고 로그 쌓기
        LogRemittance.success(settlementPurchase)
    }

    suspend fun sendMoney() {
        post<String>("https://c44ef52b-2ba6-4fbe-a84e-3c04493da0da.mock.pstmn.io/sol/gateway/webilling_wapi.jsp", LinkedMultiValueMap())
    }

    private suspend inline fun <reified T : Any> post(url: String, body: MultiValueMap<String, String>): T? =
        webClient.post()
            .uri(url)
            .body(BodyInserters.fromFormData(body))
            .retrieve()
            .bodyToMono<T>().awaitFirstOrNull()
}
