package dev.donghyeon.dev.coroutines

import kotlinx.coroutines.delay

class Coroutines {
    suspend fun process(batchName: String) {
        for (i in 1..50) {
            delay(10)
            println("Processed task $i for batch $batchName on thread ${Thread.currentThread().name}")
        }
    }
}
