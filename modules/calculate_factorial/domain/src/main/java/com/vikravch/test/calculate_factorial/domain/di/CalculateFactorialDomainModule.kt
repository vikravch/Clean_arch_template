package com.vikravch.test.calculate_factorial.domain.di

import com.vikravch.test.calculate_factorial.domain.repository.QuoteRepository
import com.vikravch.test.calculate_factorial.domain.use_case.CalculateNewFactorial
import com.vikravch.test.calculate_factorial.domain.use_case.GetNewQuote
import com.vikravch.test.calculate_factorial.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CalculateFactorialDomainModule {

    @Provides
    @Singleton
    fun provideUseCases(quoteRepository: QuoteRepository): UseCases{
        return UseCases(
            calculateNewFactorial = CalculateNewFactorial(),
            getNewQuote = GetNewQuote(quoteRepository)
        )
    }
}