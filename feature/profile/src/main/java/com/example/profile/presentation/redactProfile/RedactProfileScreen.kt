package com.example.profile.presentation.redactProfile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.busol.habittracker.core.ui.components.BaseScreen
import com.busol.habittracker.core.ui.theme.AppTheme
import com.busol.habittracker.feature.routinedetails.R
import kotlin.reflect.KFunction1

@Composable
fun RedactProfileScreen() {
    BaseScreen<RedactProfileViewModel> { viewModel ->
        val state by viewModel.state.collectAsState()

        RedactProfileContent(
            state = state,
            onNameChange = viewModel::onNameChanged,
            onSaveChanges = viewModel::onSave,
        )
    }
}

@Composable
fun RedactProfileContent(
    state: RedactProfileState,
    onNameChange: (String) -> Unit,
    onSaveChanges: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.edit_profile),
                style = AppTheme.fonts.h1,
                color = AppTheme.colors.contentColor,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.change_text_of_profile),
                style = AppTheme.fonts.h2,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.contentColor, modifier = Modifier.padding(horizontal = 32.dp),
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextField(
                value = state.nickname,
                onValueChange = onNameChange,
                label = { Text(stringResource(R.string.nickname)) },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onSaveChanges,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(stringResource(R.string.save_changes))
            }
        }
    }
}