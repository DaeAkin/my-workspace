package dev.donghyeon.dev.tutorials.kotlin.thenewboston.service

import dev.donghyeon.dev.tutorials.kotlin.thenewboston.datasource.BankDataSource
import dev.donghyeon.dev.tutorials.kotlin.thenewboston.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(
    private val dataSource: BankDataSource
) {
    
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.createBank(bank)
}
