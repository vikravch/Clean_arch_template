package com.vikravch.temp.calculate_factorial.data.fake

import com.vikravch.test.calculate_factorial.domain.model.Quote
import com.vikravch.test.calculate_factorial.domain.repository.QuoteRepository

class QuoteRepositoryFake: QuoteRepository {
    override suspend fun getQuote(): Result<Quote> {
        return Result.success(Quote("test"))
    }
}