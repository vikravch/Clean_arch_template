package com.vikravch.test.calculate_factorial.domain.use_case

import com.vikravch.test.calculate_factorial.domain.model.Quote
import com.vikravch.test.calculate_factorial.domain.repository.QuoteRepository

class GetNewQuote(private val quoteRepository: QuoteRepository) {
    suspend operator fun invoke(): Result<Quote>{
        return quoteRepository.getQuote()
    }
}