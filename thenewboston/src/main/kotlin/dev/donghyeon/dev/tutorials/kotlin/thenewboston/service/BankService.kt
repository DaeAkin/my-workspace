package dev.donghyeon.dev.tutorials.kotlin.thenewboston.service

import dev.donghyeon.dev.tutorials.kotlin.thenewboston.datasource.BankDataSource
import dev.donghyeon.dev.tutorials.kotlin.thenewboston.model.Bank
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(
    @Qualifier("mockBankDataSource")
    private val dataSource: BankDataSource
) {
    
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.createBank(bank)
    fun updateBank(bank: Bank): Bank = dataSource.updateBank(bank)
    
    fun deleteBank(accountNumber: String): Unit = dataSource.deleteBank(accountNumber)
    
}
