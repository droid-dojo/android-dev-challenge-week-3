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
package com.example.androiddevchallenge.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cabin
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Spa
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androiddevchallenge.R

sealed class Screen(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object Welcome : Screen("welcome", R.string.welcome, Icons.Default.Cabin)
    object Login : Screen("login", R.string.login, Icons.Default.Login)
    object Home : Screen("home", R.string.home, Icons.Default.Spa)
    object Profile : Screen("profile", R.string.profile, Icons.Default.AccountCircle)
}
