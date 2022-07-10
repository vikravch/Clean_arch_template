package com.vikravch.temp.calculate_factorial.presentation.calculate_factorial

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.receiveAsFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikravch.test.calculate_factorial.domain.use_case.CalculateNewFactorial
import com.vikravch.test.calculate_factorial.domain.use_case.GetNewQuote
import com.vikravch.test.calculate_factorial.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculateFactorialPageViewModel @Inject constructor(
     private val useCases: UseCases
):ViewModel() {

    var state by mutableStateOf(CalculateFactorialState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: CalculateFactorialEvent) {
        when (event) {
            is CalculateFactorialEvent.CalculateFactorial -> {
                val res = useCases.calculateNewFactorial(event.number)
                state = state.copy(result = res)
            }
            is CalculateFactorialEvent.LoadNewQuote -> {
                viewModelScope.launch {
                    val response = useCases.getNewQuote()
                    response.onSuccess {
                        state = state.copy(quote = it.quote)
                    }.onFailure {
                        _uiEvent.send(UiEvent.QuoteLoadedError)
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        object QuoteLoadedError: UiEvent()
    }
}