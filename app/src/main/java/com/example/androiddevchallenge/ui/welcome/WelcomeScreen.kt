/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.common.LargeButton
import com.example.androiddevchallenge.ui.theme.MySootheTheme

@Composable
fun WelcomeScreen(navController: NavController, darkTheme: Boolean = isSystemInDarkTheme()) {
    Box(modifier = Modifier.fillMaxSize()) {
        WelcomeBackground(darkTheme)
        WelcomeContent(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            navController = navController,
            darkTheme = darkTheme
        )
    }
}

@Composable
fun WelcomeContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = if (darkTheme) R.drawable.dark_logo else R.drawable.light_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(32.dp))
        LargeButton(wording = R.string.sign_up, onClick = { /*TODO*/ })
        Spacer(modifier = Modifier.size(8.dp))
        LargeButton(
            wording = R.string.login,
            backgroundColor = MaterialTheme.colors.secondary,
            onClick = { navController.navigate(Screen.Login.route) }
        )
    }
}

@Composable
fun WelcomeBackground(darkTheme: Boolean = isSystemInDarkTheme()) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = if (darkTheme) R.drawable.dark_welcome else R.drawable.light_welcome),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MySootheTheme {
        Surface(color = MaterialTheme.colors.background) {
            WelcomeScreen(navController = rememberNavController())
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MySootheTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            WelcomeScreen(navController = rememberNavController(), darkTheme = true)
        }
    }
}
