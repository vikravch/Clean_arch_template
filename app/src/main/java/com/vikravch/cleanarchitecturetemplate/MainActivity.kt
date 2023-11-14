package com.vikravch.cleanarchitecturetemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vikravch.temp.calculate_factorial.presentation.calculate_factorial.CalculateFactorialPage
import com.vikravch.temp.calculate_factorial.presentation.calculate_factorial.CalculateFactorialPageViewModel
import com.vikravch.temp.core.presentation.theme.CleanArchitectureTemplateTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            MainActivityContent(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
    }
}

@Composable
fun MainActivityContent(
    navController: NavHostController,
    viewModel: CalculateFactorialPageViewModel
    ) {
    CleanArchitectureTemplateTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            CalculateFactorialPage(
                viewModel = viewModel,
            )
        }
    }
}