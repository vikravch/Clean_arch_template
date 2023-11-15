package com.vikravch.cleanarchitecturetemplate.main_activity

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vikravch.cleanarchitecturetemplate.MainActivity
import com.vikravch.cleanarchitecturetemplate.MainActivityContent
import com.vikravch.cleanarchitecturetemplate.clearAndSetContent
import com.vikravch.temp.calculate_factorial.data.fake.QuoteRepositoryFake
import com.vikravch.temp.calculate_factorial.presentation.calculate_factorial.CalculateFactorialPageViewModel
import com.vikravch.test.calculate_factorial.domain.repository.QuoteRepository
import com.vikravch.test.calculate_factorial.domain.use_case.CalculateNewFactorial
import com.vikravch.test.calculate_factorial.domain.use_case.GetNewQuote
import com.vikravch.test.calculate_factorial.domain.use_case.UseCases
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CalculateFactorialPageScreenE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavHostController
    private lateinit var quoteRepository: QuoteRepository
    private lateinit var useCases: UseCases
    private lateinit var viewModel: CalculateFactorialPageViewModel

    @Before
    fun setup() {
        quoteRepository = QuoteRepositoryFake()
        useCases = UseCases(
            calculateNewFactorial = CalculateNewFactorial(),
            getNewQuote = GetNewQuote(quoteRepository)
        )
        viewModel = CalculateFactorialPageViewModel(useCases)
        composeRule.clearAndSetContent {
            navController = rememberNavController()
            MainActivityContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    }

    @Test
    fun calculateFactorialPageScreen_getQuote() {
        composeRule.onNodeWithText("Get New Quote").performClick()
        composeRule.onNodeWithText("test").assertExists()
    }

    @Test
    fun calculateFactorialPageScreen_calculateFactorial() {
        composeRule.onNodeWithTag("factorial_input").performTextInput("5")
        composeRule.onNodeWithText("Calculate").performClick()
        composeRule.onNodeWithText("Result = 120").assertExists()
    }
}