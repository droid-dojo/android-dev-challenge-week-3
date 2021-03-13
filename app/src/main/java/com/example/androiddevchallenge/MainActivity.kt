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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.home.HomeScreen
import com.example.androiddevchallenge.ui.login.LoginScreen
import com.example.androiddevchallenge.ui.theme.MySootheTheme
import com.example.androiddevchallenge.ui.utils.LocalSysUiController
import com.example.androiddevchallenge.ui.utils.SystemUiController
import com.example.androiddevchallenge.ui.welcome.WelcomeScreen
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = remember { SystemUiController(window) }
            CompositionLocalProvider(LocalSysUiController provides systemUiController) {
                systemUiController.setStatusBarColor(Color.Transparent)
                ProvideWindowInsets {
                    MySootheTheme {
                        MyApp()
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        MainNavigation()
    }
}

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) { WelcomeScreen(navController = navController) }
        composable(Screen.Login.route) { LoginScreen(navController = navController) }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

    val topDestinations = listOf(
        Screen.Home,
        Screen.Profile,
    )

    val showBottomBar = topDestinations.firstOrNull { it.route == currentRoute } != null

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(
                    elevation = 8.dp,
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    topDestinations.forEach { screen ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = screen.icon,
                                    contentDescription = stringResource(
                                        id = screen.label
                                    )
                                )
                            },
                            label = {
                                Text(
                                    stringResource(screen.label),
                                    style = MaterialTheme.typography.caption
                                )
                            },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo = navController.graph.startDestination
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            if (showBottomBar) {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Content"
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {
        NavHost(navController, startDestination = Screen.Welcome.route) {
            composable(Screen.Welcome.route) { WelcomeScreen(navController = navController) }
            composable(Screen.Login.route) { LoginScreen(navController = navController) }
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Profile.route) { TODOScreen() }
        }
    }
}

@Composable
fun TODOScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Some fancy cool content will appear here"
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MySootheTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MySootheTheme(darkTheme = true) {
        MyApp()
    }
}
