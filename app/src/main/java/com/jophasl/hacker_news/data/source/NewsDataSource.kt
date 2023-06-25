package com.jophasl.hacker_news.data.source

import com.jophasl.hacker_news.data.remote.Story
import com.jophasl.hacker_news.util.Resource
import kotlinx.coroutines.flow.StateFlow

interface NewsDataSource {
    var pageSize: Int

    suspend fun getNews(page: Int): Resource<List<Story>>

    suspend fun refreshNews()

    suspend fun getStory(storyId: Int): Resource<Story>

    suspend fun refreshStory(storyId: Int)

    suspend fun saveStory(story: Story)

    suspend fun deleteAllNews()

    suspend fun deleteStory(storyId: Int)
}
