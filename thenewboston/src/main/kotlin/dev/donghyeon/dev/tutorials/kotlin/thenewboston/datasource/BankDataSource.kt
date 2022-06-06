package dev.donghyeon.dev.tutorials.kotlin.thenewboston.datasource

import dev.donghyeon.dev.tutorials.kotlin.thenewboston.model.Bank

interface BankDataSource {
    
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun createBank(bank: Bank): Bank
}
