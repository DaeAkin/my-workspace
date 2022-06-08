package dev.donghyeon.dev.tutorials.kotlin.thenewboston.datasource.network

import dev.donghyeon.dev.tutorials.kotlin.thenewboston.datasource.BankDataSource
import dev.donghyeon.dev.tutorials.kotlin.thenewboston.model.Bank
import org.springframework.stereotype.Repository

@Repository("network")
class NetworkDataSource : BankDataSource {
    override fun retrieveBanks(): Collection<Bank> {
        TODO("Not yet implemented")
    }

    override fun retrieveBank(accountNumber: String): Bank {
        TODO("Not yet implemented")
    }

    override fun createBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun updateBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String) {
        TODO("Not yet implemented")
    }
}
