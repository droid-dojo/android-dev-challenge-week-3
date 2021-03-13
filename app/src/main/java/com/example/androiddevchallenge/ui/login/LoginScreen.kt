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
package com.example.androiddevchallenge.ui.login

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.common.LargeButton
import com.example.androiddevchallenge.ui.theme.MySootheTheme

@Composable
fun LoginScreen(darkTheme: Boolean = isSystemInDarkTheme(), navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        LoginBackground(darkTheme)
        LoginContent(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            navController = navController
        )
    }
}

@Composable
fun LoginContent(modifier: Modifier = Modifier, navController: NavController) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.login).toUpperCase(),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.size(32.dp))
        LargeInputField(
            hint = R.string.email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.size(8.dp))
        LargeInputField(
            hint = R.string.password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.size(8.dp))
        LargeButton(
            wording = R.string.login,
            onClick = { navController.navigate(Screen.Home.route) }
        )

        Row(
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.no_account),
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.clickable { },
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.body1,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
fun LargeInputField(
    @StringRes hint: Int,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var input by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = input,
        onValueChange = { input = it },
        textStyle = MaterialTheme.typography.body1,
        placeholder = { Text(text = stringResource(id = hint)) },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}

@Composable
fun LoginBackground(darkTheme: Boolean = isSystemInDarkTheme()) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = if (darkTheme) R.drawable.dark_login else R.drawable.light_login),
        contentDescription = null
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MySootheTheme {
        Surface(color = MaterialTheme.colors.background) {
            LoginScreen(navController = rememberNavController())
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MySootheTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            LoginScreen(darkTheme = true, navController = rememberNavController())
        }
    }
}
