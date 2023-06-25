package com.jophasl.hacker_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.jophasl.hacker_news.data.HackerNewsRepository
import com.jophasl.hacker_news.data.remote.RemoteNewsDataSource
import com.jophasl.hacker_news.ui.composables.NewsPage
import com.jophasl.hacker_news.ui.models.NewsViewModel
import com.jophasl.hacker_news.ui.theme.Hacker_newsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hacker_newsTheme {
                // A surface container using the 'background' color from the theme
                val dataSource = remember { RemoteNewsDataSource() }
                val repo by remember { mutableStateOf(HackerNewsRepository(dataSource)) }
                val model by remember { mutableStateOf(NewsViewModel(repo)) }
                NewsPage(newsViewModel = model)
            }
        }
    }
}

@Preview
@Composable
fun PreviewHackerItem() {

}
