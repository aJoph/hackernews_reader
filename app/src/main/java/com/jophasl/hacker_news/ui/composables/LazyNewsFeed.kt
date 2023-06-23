package com.jophasl.hacker_news.ui.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.jophasl.hacker_news.data.remote.Story
import kotlinx.coroutines.launch

@Composable
fun LazyNewsFeed(
    news: List<Story>, scrollState: LazyListState, onReachedEnd: (() -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    var endIndex by remember { mutableStateOf(1) }
    LaunchedEffect(endIndex) {
        scope.launch {
            scrollState.animateScrollToItem(endIndex - 1)
        }
    }
    LazyColumn(state = scrollState) {
        itemsIndexed(news) { i, story ->
            if (story == news.last()) {
                endIndex = i
                onReachedEnd?.invoke()
            }

            NewsTile(data = story)
        }
    }

}