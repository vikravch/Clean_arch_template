package com.vikravch.test.calculate_factorial.domain.use_case

import java.math.BigInteger

class CalculateNewFactorial {
    operator fun invoke(number:Int):Long{
        var factorial = BigInteger.ONE
        for (i in 1..number) {
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        return factorial.toLong()
    }
}