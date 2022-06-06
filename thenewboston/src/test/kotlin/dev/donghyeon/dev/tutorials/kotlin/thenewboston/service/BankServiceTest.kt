package dev.donghyeon.dev.tutorials.kotlin.thenewboston.service

import dev.donghyeon.dev.tutorials.kotlin.thenewboston.datasource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    /**
     * every { dataSource.retrieveBanks() } returns emptyList()
     * mockk(relaxed = true)를 사용하면 특정 메서드가 호출될 때마다 랜덤 value가 제공된다.
     */
    private val dataSource : BankDataSource = mockk(relaxed = true)
    private val bankService = BankService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        //when
        bankService.getBanks()
        
        //then
        verify(exactly = 1) { dataSource.retrieveBanks() }
        
    }
}
