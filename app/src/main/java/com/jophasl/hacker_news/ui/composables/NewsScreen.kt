package com.jophasl.hacker_news.ui.composables

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.jophasl.hacker_news.data.HackerNewsRepository
import com.jophasl.hacker_news.data.remote.HackerNewsApi
import com.jophasl.hacker_news.domain.api.RetrofitClient
import com.jophasl.hacker_news.ui.models.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsPage(
    newsViewModel: NewsViewModel,
) {
    val newsList by newsViewModel.newsList.collectAsState()
    val scrollState = rememberLazyListState()
    Surface {
        TopAppBar(
            title = { Text("HackerNews's Best") }
        )
        LazyNewsFeed(news = newsList, scrollState = scrollState)
    }
}

@Preview
@Composable
fun PreviewNewsPage() {
    val api = RetrofitClient.getClient().create(HackerNewsApi::class.java)
    val model = NewsViewModel(
        HackerNewsRepository(
            api
        )
    )
    NewsPage(model)
}