package com.vikravch.test.calculate_factorial.domain

import com.vikravch.test.calculate_factorial.domain.use_case.CalculateNewFactorial
import org.junit.Test

class CalculateNewFactorialTest {

    @Test
    fun `calculate factorial`() {
        // given
        val number = 5
        val calculateNewFactorial = CalculateNewFactorial()

        // when
        val result = calculateNewFactorial(number)

        // then
        assert(result == 120L)
    }

    @Test
    fun `calculate factorial with zero`() {
        // given
        val number = 0
        val calculateNewFactorial = CalculateNewFactorial()

        // when
        val result = calculateNewFactorial(number)

        // then
        assert(result == 1L)
    }

    @Test
    fun `calculate factorial with one`() {
        // given
        val number = 1
        val calculateNewFactorial = CalculateNewFactorial()

        // when
        val result = calculateNewFactorial(number)

        // then
        assert(result == 1L)
    }

    @Test
    fun `calculate factorial with minus`() {
        // given
        val number = -1
        val calculateNewFactorial = CalculateNewFactorial()

        // when
        val result = calculateNewFactorial(number)

        // then
        assert(result == 1L)
    }

    @Test
    fun `calculate factorial with big number`() {
        // given
        val number = 100
        val calculateNewFactorial = CalculateNewFactorial()

        // when
        val result = calculateNewFactorial(number)

        // then
        assert(result == 0L)
    }
}