package com.jophasl.hacker_news.ui.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import com.jophasl.hacker_news.data.remote.Story

@Composable
fun LazyNewsFeed(news: List<Story>, scrollState: LazyListState) {
    LazyColumn(state = scrollState) {
        items(news) {story ->
            NewsTile(data = story)
        }
    }
}