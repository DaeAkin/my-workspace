package dev.donghyeon.dev.tutorials.kotlin.thenewboston.datasource.mock

import dev.donghyeon.dev.tutorials.kotlin.thenewboston.datasource.BankDataSource
import dev.donghyeon.dev.tutorials.kotlin.thenewboston.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import kotlin.NoSuchElementException

@Repository
class MockBankDataSource : BankDataSource {
    val banks = mutableListOf(
        Bank("1234", 1.0, 1),
        Bank("1010", 17.0, 2),
        Bank("4122", 1.0, 100)
    )
    
    override fun retrieveBanks(): Collection<Bank> = banks
    
    override fun retrieveBank(accountNumber: String): Bank {
        return banks.firstOrNull() {it.accountNumber == accountNumber}
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
    }

    override fun createBank(bank: Bank): Bank {
        if (banks.any {it.accountNumber == bank.accountNumber}) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists.")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull {
            it.accountNumber == bank.accountNumber
        } ?: throw NoSuchElementException("Could not find a bank with account name")
        banks.remove(currentBank)
        banks.add(bank)
        
        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = banks.firstOrNull {
            it.accountNumber == accountNumber
        } ?: throw NoSuchElementException("Could not find a bank with account name")
        
        banks.remove(currentBank)
    }
}
