package com.busol.habittracker.core.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<State: Any> : ViewModel() {

    private val mutableState = MutableStateFlow(this.createInitialState())
    val state = mutableState.asStateFlow()

    abstract fun createInitialState(): State

    protected fun updateState(block: (State) -> State) {
        mutableState.update(block)
    }

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(
            context = context,
            start = start,
            block = block
        )
    }
}