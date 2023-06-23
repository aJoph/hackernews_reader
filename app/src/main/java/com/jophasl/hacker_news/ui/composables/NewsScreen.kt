package com.jophasl.hacker_news.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jophasl.hacker_news.data.HackerNewsRepository
import com.jophasl.hacker_news.data.remote.HackerNewsApi
import com.jophasl.hacker_news.domain.api.RetrofitClient
import com.jophasl.hacker_news.ui.models.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsPage(
    newsViewModel: NewsViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(newsViewModel.isLoading.value) {
        snackbarHostState.showSnackbar(
            message = "Loading more stories!",
        )
    }

    val newsList by newsViewModel.newsList.collectAsState()
    val scrollState = rememberLazyListState()
    Surface {
        Column {
            TopAppBar(title = { Text("HackerNews's Best") })

            LazyNewsFeed(news = newsList, scrollState = scrollState, onReachedEnd = {
                newsViewModel.fetchMoreStories()
            })

        }
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