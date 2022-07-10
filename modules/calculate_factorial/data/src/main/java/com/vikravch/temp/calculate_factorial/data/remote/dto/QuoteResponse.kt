package com.vikravch.temp.calculate_factorial.data.remote.dto

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("quote") val quote: String
)