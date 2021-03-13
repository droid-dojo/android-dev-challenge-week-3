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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.ui.theme.MySootheTheme
import dev.chrisbanes.accompanist.coil.CoilImage

val alignYourBody = listOf(

    ActivityItem(
        imageSource = "https://images.pexels.com/photos/317157/pexels-photo-317157.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "Inversions"
    ),
    ActivityItem(

        imageSource = "https://images.pexels.com/photos/1812964/pexels-photo-1812964.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "Quick Yoga"
    ),
    ActivityItem(

        imageSource = "https://images.pexels.com/photos/4056723/pexels-photo-4056723.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",

        title = "Stretching"
    ),
    ActivityItem(

        imageSource = "https://images.pexels.com/photos/4662438/pexels-photo-4662438.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",

        title = "Tabata"
    ),
    ActivityItem(

        imageSource = "https://images.pexels.com/photos/999309/pexels-photo-999309.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",

        title = "HIIT"
    ),
    ActivityItem(

        imageSource = "https://images.pexels.com/photos/396133/pexels-photo-396133.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",

        title = "Pre-natal yoga"
    ),
)

val alignYourMind = listOf(
    ActivityItem(

        imageSource = "https://images.pexels.com/photos/3822622/pexels-photo-3822622.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",

        title = "Meditate"
    ),
    ActivityItem(

        imageSource = "https://images.pexels.com/photos/3094230/pexels-photo-3094230.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",

        title = "With kids"
    ),
    ActivityItem(
        imageSource = "https://images.pexels.com/photos/4498318/pexels-photo-4498318.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",

        title = "Aromatheraphy"
    ),
    ActivityItem(

        imageSource = "https://images.pexels.com/photos/1241348/pexels-photo-1241348.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "On the go"
    ),
    ActivityItem(

        imageSource = "https://images.pexels.com/photos/4056535/pexels-photo-4056535.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "With pets"
    ),
    ActivityItem(
        imageSource = "https://images.pexels.com/photos/897817/pexels-photo-897817.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        title = "High stress"
    ),
)

data class ActivityItem(val imageSource: String, val title: String)

@Composable
fun Activity(item: ActivityItem) {
    Column(
        modifier = Modifier.wrapContentSize().clickable { },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        CoilImage(
            modifier = Modifier
                .height(88.dp)
                .aspectRatio(1f),
            data = item.imageSource,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            requestBuilder = {
                transformations(CircleCropTransformation())
            },
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colors.secondary, shape = CircleShape)
                )
            }
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .wrapContentHeight(align = Alignment.Bottom),
            text = item.title,
            style = MaterialTheme.typography.h3
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActivityList(activities: List<ActivityItem>) {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(activities) {
            Activity(item = it)
        }
    }
}

@Preview("Light Theme List")
@Composable
fun LightActivityListPreview() {
    MySootheTheme {
        ActivityList(alignYourBody)
    }
}

@Preview("Dark Theme List")
@Composable
fun DarkActivityListPreview() {
    MySootheTheme(darkTheme = true) {
        ActivityList(alignYourMind)
    }
}

@Preview("Light Theme")
@Composable
fun LightActivityPreview() {
    MySootheTheme {
        Activity(alignYourBody.first())
    }
}

@Preview("Dark Theme")
@Composable
fun DarkActivityPreview() {
    MySootheTheme(darkTheme = true) {
        Activity(alignYourMind.last())
    }
}
