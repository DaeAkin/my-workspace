package dev.donghyeon.dev.tutorials.kotlin.thenewboston.datasource.mock


import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.*

internal class MockBankDataSourceTest {
    
    private val mockDataSource = MockBankDataSource();
    
    @Test
    fun `should provide a collection of banks`() {
        //when
        val banks = mockDataSource.retrieveBanks();
        
        //then
        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }
    
    @Test
    fun `should provide some mock data`() {
        //when
        val banks = mockDataSource.retrieveBanks()
        
        //then
        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        assertThat(banks).anyMatch { it.trust != 0.0 }
        assertThat(banks).allMatch { it.transactionFee != 0 }
        
    }
}
