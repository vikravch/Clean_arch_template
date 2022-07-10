package com.vikravch.test.calculate_factorial.domain.repository

import com.vikravch.test.calculate_factorial.domain.model.Quote

interface QuoteRepository {
    suspend fun getQuote():Result<Quote>
}