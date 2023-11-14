package com.vikravch.temp.calculate_factorial.data

import com.vikravch.temp.calculate_factorial.data.remote.QuoteAPI
import com.vikravch.temp.calculate_factorial.data.repository.QuoteRepositoryNetwork
import com.vikravch.test.calculate_factorial.domain.model.Quote
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class QuoteRepositoryNetworkTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: QuoteAPI
    private lateinit var quoteRepositoryNetwork: QuoteRepositoryNetwork

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(QuoteAPI::class.java)
        quoteRepositoryNetwork = QuoteRepositoryNetwork(
            quoteAPI = api
        )
    }
    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get quote`() = runBlocking {
        // given
        val quote = "test"
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    """
                    {
                        "quote": "$quote"
                    }
                    """.trimIndent()
                )
        )

        // when
        val result = quoteRepositoryNetwork.getQuote()

        // then
        assert(result == Result.success(Quote(quote = quote)))
    }
}