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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MySootheTheme
import com.example.androiddevchallenge.ui.theme.shapes
import dev.chrisbanes.accompanist.coil.CoilImage

val sampleCollections = listOf(

    CollectionItem(
        imageSource = "https://images.pexels.com/photos/1825206/pexels-photo-1825206.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "Short Mantras"
    ),
    CollectionItem(
        imageSource = "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "Nature Mediations"
    ),
    CollectionItem(
        imageSource = "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "Stress and anxiety"
    ),
    CollectionItem(
        imageSource = "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "Self-massage"
    ),
    CollectionItem(
        imageSource = "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "Overwhelmed"
    ),
    CollectionItem(
        imageSource = "https://images.pexels.com/photos/924824/pexels-photo-924824.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "Nightly wind down"
    ),
)

data class CollectionItem(val imageSource: String, val title: String)

@Composable
fun Collection(modifier: Modifier = Modifier, item: CollectionItem) {
    Card(
        modifier = Modifier
            .size(width = 192.dp, height = 56.dp)
            .clip(MaterialTheme.shapes.small)
            .clickable { }
            .then(modifier),
        shape = MaterialTheme.shapes.small,
        elevation = 0.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CoilImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                data = item.imageSource,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = item.title,
                style = MaterialTheme.typography.h3,
                maxLines = 2,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollectionList(collections: List<CollectionItem>) {

    val firstLine = collections.filterIndexed { index, _ -> index % 2 == 0 }
    val secondLine = collections.filterNot { firstLine.contains(it) }

    val scrollState = rememberScrollState(0)

    Column(modifier = Modifier.horizontalScroll(scrollState)) {

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            firstLine.forEach { Collection(item = it) }
        }

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            secondLine.forEach { Collection(item = it) }
        }
    }
}

@Preview("Light Theme List")
@Composable
fun LightCollectionListPreview() {
    MySootheTheme {
        CollectionList(sampleCollections)
    }
}

@Preview("Dark Theme List")
@Composable
fun DarkCollectionListPreview() {
    MySootheTheme(darkTheme = true) {
        CollectionList(collections = sampleCollections)
    }
}

@Preview("Light Theme")
@Composable
fun LightCollectionPreview() {
    MySootheTheme {
        Collection(item = sampleCollections.first())
    }
}

@Preview("Dark Theme")
@Composable
fun DarkCollectionPreview() {
    MySootheTheme(darkTheme = true) {
        Collection(item = sampleCollections.last())
    }
}
