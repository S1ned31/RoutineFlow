package com.example.profile.presentation.profile

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.busol.habittracker.core.ui.components.BaseScreen
import com.busol.habittracker.core.ui.components.bottomBar.HabitBottomBar
import com.busol.habittracker.core.ui.components.bottomBar.HabitBottomBarItems
import com.busol.habittracker.core.ui.theme.AppTheme
import com.busol.habittracker.feature.routinedetails.R

@Composable
fun ProfileScreen() {
    BaseScreen<ProfileViewModel> { viewModel ->
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.update()
        }
        ProfileContent(
            state = state,
            redactProfile = viewModel::redactProfile,
            signOut = viewModel::signOut,
            navigate = viewModel::navigate
        )
    }
}

@Composable
fun ProfileContent(
    state: ProfileState,
    redactProfile: () -> Unit,
    signOut: () -> Unit,
    navigate: (String) -> Unit,
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            HabitBottomBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = AppTheme.colors.containerColor,
                bottomBarItems = HabitBottomBarItems(
                    homeTitle = "Home",
                    feedTitle = "Feed",
                    profileTitle = "Profile",
                ).items,
                currentEntry = navController.currentBackStackEntry,
                navigate = navigate,
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (state.image == "") {
                Image(
                    imageVector = ImageVector.vectorResource(id = com.busol.habittracker.core.ui.R.drawable.user),
                    contentDescription = "Profile Icon",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                )
            } else {
                AsyncImage(
                    model = state.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Danylo",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "test@gmail.com",
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = redactProfile,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(R.string.redact_profile))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = signOut,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(R.string.sign_out))
            }
        }
    }
}