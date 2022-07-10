package com.vikravch.temp.calculate_factorial.data.repository

import com.vikravch.temp.calculate_factorial.data.mapper.QuoteResponseToQuote
import com.vikravch.temp.calculate_factorial.data.remote.QuoteAPI
import com.vikravch.test.calculate_factorial.domain.model.Quote
import com.vikravch.test.calculate_factorial.domain.repository.QuoteRepository
import java.lang.Exception

class QuoteRepositoryNetwork(val quoteAPI: QuoteAPI): QuoteRepository {
    override suspend fun getQuote(): Result<Quote> {
        return try{
            val res = quoteAPI.getQuote()
            if(res.isSuccessful){
                res.body()?.let {
                    Result.success(QuoteResponseToQuote.map(it))
                }?:run{
                    Result.failure(Exception(""))
                }
            } else {
                Result.failure(Exception(""))
            }
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}