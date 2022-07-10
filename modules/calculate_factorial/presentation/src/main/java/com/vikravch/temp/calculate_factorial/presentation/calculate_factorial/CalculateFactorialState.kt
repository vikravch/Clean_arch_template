package com.vikravch.temp.calculate_factorial.presentation.calculate_factorial

data class CalculateFactorialState(
    val loading: Boolean = false,
    val quote: String = "",
    val result: Long = 0L,
)