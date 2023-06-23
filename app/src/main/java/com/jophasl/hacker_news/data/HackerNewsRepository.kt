package com.jophasl.hacker_news.data

import com.jophasl.hacker_news.data.remote.HackerNewsApi
import com.jophasl.hacker_news.data.remote.Story
import com.jophasl.hacker_news.data.remote.getBestStories
import com.jophasl.hacker_news.util.Resource

class HackerNewsRepository(
    private val api: HackerNewsApi
) {
    suspend fun getFrontPage(): Resource<List<Story>> {
        val response = try {
            api.getBestStories()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "Unexpected error fetching front page.")
        }

        if (!response.isSuccessful || response.body() == null) return Resource.Error("Failed to fetch list front page")
        return Resource.Success(response.body()!!)
    }

}

