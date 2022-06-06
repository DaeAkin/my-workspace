package dev.donghyeon.dev.tutorials.kotlin.thenewboston.controller

import com.fasterxml.jackson.databind.ObjectMapper
import dev.donghyeon.dev.tutorials.kotlin.thenewboston.model.Bank
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl = "/api/banks"
    
    @Nested
    @DisplayName("getBanks()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            //when/then
            mockMvc.get("$baseUrl")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber"){value("1234")}
                }
        }   
    }

    @Nested
    @DisplayName("getBank()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given account number`() {
            //given
            val accountNumber = 1234

            //when
            mockMvc.get("$baseUrl/$accountNumber")
                //then
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value("1.0") }
                    jsonPath("$.transactionFee") {value("1")}
                }
        }
        
        @Test
        fun `should return Not Found if the account number does not exist`() {
            //given
            val accountNumber = "does_not_exist"
            
            //when
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
            
            //then
        }
        
        @Nested
        @DisplayName("POST /api/banks")
        @TestInstance(Lifecycle.PER_CLASS)
        inner class PostNewBank{
            
            @Test
            fun `should add the new bank`() {
                //given
                val newBank = Bank("acc123", 31.415, 2)
                
                //when
                val performPost = mockMvc.post(baseUrl) {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(newBank)
                }
                //then
                performPost
                    .andDo { print() }
                    .andExpect { 
                        status { isCreated() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$.accountNumber") { value("acc1234") }
                        jsonPath("$.trust") { value("31.415") }
                        jsonPath("$.transactionFee") { value("2") }
                    }
                
            }
            
            @Test
            fun `should return BAD Request if bank with given account number already exists`() {
                //given
                val invalidBank = Bank("1234", 1.0, 1)
                
                //when
                val performPost = mockMvc.post(baseUrl) {
                    contentType = MediaType.APPLICATION_JSON
                    content = objectMapper.writeValueAsString(invalidBank)
                }
                
                //then
                performPost
                    .andDo { print() }
                    .andExpect { status { isBadRequest() } }
                
            }
        }
    }
    
    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PatchExistingBank {
        
        @Test
        fun `should update an existing bank`() {
            //given
            val updateBank = Bank("1234", 1.0, 1)
            
            //when
            val performPatch = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateBank)
            }

            //then
            performPatch
                .andDo { print() }
                .andExpect { 
                    status { isOk() }
                    content { 
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updateBank))
                    }
                }
            
        }
        
    }
    
    
}
