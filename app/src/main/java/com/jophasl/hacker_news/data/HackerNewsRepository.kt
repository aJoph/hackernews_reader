package com.jophasl.hacker_news.data

import com.jophasl.hacker_news.data.remote.RemoteNewsDataSource
import com.jophasl.hacker_news.data.remote.Story
import com.jophasl.hacker_news.data.source.NewsDataSource
import com.jophasl.hacker_news.util.Resource

class HackerNewsRepository(
    private val newsDataSource: NewsDataSource = RemoteNewsDataSource()
) {
    /// Fetches entire list of best stories, but only converts those that match the
    /// page and size.
    suspend fun getBestStories(page: Int, size: Int): Resource<List<Story>> {
        return newsDataSource.apply { pageSize = size }.getNews(page)
    }

    suspend fun getFrontPage(): Resource<List<Story>> {
        return  newsDataSource.apply { pageSize = 20 }.getNews(0)
    }

}

