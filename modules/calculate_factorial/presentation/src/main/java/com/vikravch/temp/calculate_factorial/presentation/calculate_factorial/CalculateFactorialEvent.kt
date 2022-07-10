package com.vikravch.temp.calculate_factorial.presentation.calculate_factorial

sealed class CalculateFactorialEvent {
    object LoadNewQuote: CalculateFactorialEvent()
    data class CalculateFactorial(val number:Int): CalculateFactorialEvent()
}