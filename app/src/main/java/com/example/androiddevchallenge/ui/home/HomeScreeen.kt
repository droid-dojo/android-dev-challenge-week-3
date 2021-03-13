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
package com.example.androiddevchallenge.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MySootheTheme

@Composable
fun HomeScreen() {

    val scrollState = rememberScrollState(0)

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
//            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.size(56.dp))
        SearchBar()
        Text(
            modifier = Modifier.fillMaxWidth().height(40.dp).wrapContentHeight(align = Alignment.Bottom),
            text = stringResource(id = R.string.favorite_collections).toUpperCase(),
            style = MaterialTheme.typography.h2,
        )

        CollectionList(sampleCollections)

        Text(
            modifier = Modifier.fillMaxWidth().height(40.dp).wrapContentHeight(align = Alignment.Bottom),
            text = stringResource(id = R.string.align_your_body).toUpperCase(),
            style = MaterialTheme.typography.h2,
        )

        ActivityList(activities = alignYourBody)

        Text(
            modifier = Modifier.fillMaxWidth().height(40.dp).wrapContentHeight(align = Alignment.Bottom),
            text = stringResource(id = R.string.align_your_mind).toUpperCase(),
            style = MaterialTheme.typography.h2,
        )

        ActivityList(activities = alignYourMind)
    }
}

@Composable
fun SearchBar(
    @StringRes hint: Int = R.string.search
) {
    var input by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = input,
        onValueChange = { input = it },
        textStyle = MaterialTheme.typography.body1,
        placeholder = { Text(text = stringResource(id = hint)) },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MySootheTheme {
        Surface(color = MaterialTheme.colors.background) {
            HomeScreen()
        }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MySootheTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            HomeScreen()
        }
    }
}
