package com.vikravch.temp.calculate_factorial.presentation.calculate_factorial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.vikravch.temp.core.presentation.theme.CleanArchitectureTemplateTheme

@Composable
fun CalculateFactorialPage(
    viewModel: CalculateFactorialPageViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(Unit){
        viewModel.onEvent(CalculateFactorialEvent.LoadNewQuote)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val topGuideline = createGuidelineFromTop(0.45f)
        val startGuideline = createGuidelineFromStart(24.dp)
        val endGuideline = createGuidelineFromEnd(24.dp)

        val (title, etInput, btnCalculate, tvResult, tvQuote, btnGetQuote) = createRefs()
        val (text, setText) = remember { mutableStateOf(TextFieldValue("")) }

        Text(text = state.quote, modifier = Modifier
            .constrainAs(tvQuote) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .fillMaxWidth())
        Button(onClick = {
            viewModel.onEvent(CalculateFactorialEvent.LoadNewQuote)
        }, modifier = Modifier.constrainAs(btnGetQuote){
            top.linkTo(tvQuote.bottom, margin = 12.dp)
            start.linkTo(startGuideline)
            end.linkTo(endGuideline)}) {
            Text(text = "Get New Quote")
        }

        Text(text = "Please input number",
            modifier = Modifier.constrainAs(title){
                top.linkTo(topGuideline)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            })

        TextField(value = text, onValueChange = {
            setText(it)
        },
        modifier = Modifier.constrainAs(etInput){
            top.linkTo(title.bottom)
            start.linkTo(startGuideline)
            end.linkTo(endGuideline)
        })

        Button(onClick = {
            viewModel.onEvent(CalculateFactorialEvent.CalculateFactorial(
                number = text.text.toIntOrNull()?:-1))
        }, modifier = Modifier.constrainAs(btnCalculate){
            top.linkTo(etInput.bottom)
            start.linkTo(startGuideline)
            end.linkTo(endGuideline)
        }) {
            Text(text = "Calculate")
        }

        Text(text = "Result = ${state.result}", modifier = Modifier.constrainAs(tvResult){
            top.linkTo(btnCalculate.bottom)
            start.linkTo(startGuideline)
            end.linkTo(endGuideline)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CleanArchitectureTemplateTheme {
        CalculateFactorialPage()
    }
}