package com.busol.habittracker.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.busol.habittracker.core.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
inline fun <reified VM : BaseViewModel<*>> BaseScreen(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(AppTheme.colors.background),
    content: @Composable BoxScope.(VM) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        val viewModel: VM = koinViewModel()
        content(viewModel)
    }
}
