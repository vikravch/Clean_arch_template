package com.vikravch.temp.calculate_factorial.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.vikravch.temp.calculate_factorial.data.remote.QuoteAPI
import com.vikravch.temp.calculate_factorial.data.repository.QuoteRepositoryNetwork
import com.vikravch.test.calculate_factorial.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CalculateFactorialDataModule {

    @Singleton
    @Provides
    fun getOkHttpClient(
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor { message ->
            Log.d("httprequestlog:", message)
        }

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        builder.addInterceptor(logging)
        return builder.build()
    }

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        val gson = gsonBuilder.create()

        return Retrofit.Builder()
            .baseUrl("https://api.kanye.rest/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideQuoteAPI(retrofit: Retrofit):QuoteAPI{
        return retrofit.create(QuoteAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(quoteAPI: QuoteAPI):QuoteRepository{
        return QuoteRepositoryNetwork(quoteAPI)
    }
}