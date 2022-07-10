package com.vikravch.temp.calculate_factorial.data.remote

import com.vikravch.temp.calculate_factorial.data.remote.dto.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {

    @GET("/")
    suspend fun getQuote(): Response<QuoteResponse>

}