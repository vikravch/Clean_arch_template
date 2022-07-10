package com.vikravch.temp.calculate_factorial.data.mapper

import com.vikravch.temp.calculate_factorial.data.remote.dto.QuoteResponse
import com.vikravch.test.calculate_factorial.domain.model.Quote

object QuoteResponseToQuote {
    fun map(quoteResponse: QuoteResponse): Quote{
        return Quote(quoteResponse.quote)
    }
}