package com.jophasl.hacker_news.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jophasl.hacker_news.data.HackerNewsRepository
import com.jophasl.hacker_news.data.remote.HackerNewsApi
import com.jophasl.hacker_news.domain.api.RetrofitClient
import com.jophasl.hacker_news.ui.models.NewsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsPage(
    newsViewModel: NewsViewModel,
    onLoadingMore: (() -> Unit)? = null,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val newsList by newsViewModel.newsList.collectAsState()
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        TopAppBar(title = { Text("HackerNews' Best") })
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            LazyNewsFeed(news = newsList, scrollState = scrollState, onReachedEnd = {
                newsViewModel.fetchMoreStories()
                scope.launch {
                    snackbarHostState.showSnackbar("Loading more")

                }
            })
        }
    }

    LaunchedEffect(newsViewModel.isLoading.value) {
        if (newsViewModel.isLoading.value) onLoadingMore?.invoke()
    }
}

@Preview
@Composable
fun PreviewNewsPage() {
//    val api = RetrofitClient.getClient().create(HackerNewsApi::class.java)
//    val model = NewsViewModel(
//        HackerNewsRepository(
//            api
//        )
//    )
//    NewsPage(model)
}