package com.vikravch.test.calculate_factorial.domain

import com.vikravch.test.calculate_factorial.domain.model.Quote
import com.vikravch.test.calculate_factorial.domain.repository.QuoteRepository
import com.vikravch.test.calculate_factorial.domain.use_case.GetNewQuote
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetNewQuoteTest {

    private val quoteRepository = mockk<QuoteRepository>()
    private val getNewQuote = GetNewQuote(quoteRepository)

    @Test
    fun `get new quote`() = runBlocking {
        val newQuote = Quote(
            quote = "test",
            )
        coEvery { quoteRepository.getQuote() } returns Result.success(newQuote)

        getNewQuote()

        coVerify { quoteRepository.getQuote() }
    }

    @Test
    fun `get new quote with empty quote`() = runBlocking {
        val newQuote = Quote(
            quote = "",
            )
        coEvery { quoteRepository.getQuote() } returns Result.success(newQuote)

        getNewQuote()

        coVerify { quoteRepository.getQuote() }
    }
}