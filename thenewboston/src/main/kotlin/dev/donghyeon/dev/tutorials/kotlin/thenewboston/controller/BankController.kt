package dev.donghyeon.dev.tutorials.kotlin.thenewboston.controller

import dev.donghyeon.dev.tutorials.kotlin.thenewboston.model.Bank
import dev.donghyeon.dev.tutorials.kotlin.thenewboston.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {
    
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e : NoSuchElementException): ResponseEntity<String> = 
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e : IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    
    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()
    
    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): Bank = service.getBank(accountNumber)
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank = service.addBank(bank)
    
    @PatchMapping
    fun updateBank(@RequestBody bank: Bank): Bank = bank
}
