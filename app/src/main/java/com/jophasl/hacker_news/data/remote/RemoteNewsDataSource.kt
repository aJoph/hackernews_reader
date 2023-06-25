package com.jophasl.hacker_news.data.remote

import com.jophasl.hacker_news.data.source.NewsDataSource
import com.jophasl.hacker_news.domain.api.RetrofitClient
import com.jophasl.hacker_news.util.Resource

class RemoteNewsDataSource(override var pageSize: Int = 15) : NewsDataSource {
    private var api: HackerNewsApi = RetrofitClient.getClient().create(HackerNewsApi::class.java)

    override suspend fun getNews(page: Int): Resource<List<Story>> {
        val response = try {
            api.getBestStoriesIds()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "Unexpected error fetching best stories ids")
        }
        if (!response.isSuccessful || response.body() == null) return Resource.Error("Failed to fetch /beststories")
        val ids = response.body()!!.subList(page * pageSize, (page + 1) * pageSize)
        val stories = mutableListOf<Story>()
        ids.forEach {
            val storyRes = api.getStory(it)
            if (!storyRes.isSuccessful || storyRes.body() == null) return Resource.Error("Error fetching story body from Id")
            val story = storyRes.body()!!
            if (story.type == "story") stories.add(story.toSubtype())
        }
        return Resource.Success(stories)
    }

    override suspend fun getStory(storyId: Int): Resource<Story> {
        val storyRes = api.getStory(storyId)
        if (!storyRes.isSuccessful || storyRes.body() == null) return Resource.Error("Failed to retrieve story with id = $storyId")
        val item = storyRes.body()!!
        if (item.type != "story") return Resource.Error("Item with id = $storyId was not a Story")
        return  Resource.Success(item.toSubtype())
    }

    override suspend fun deleteAllNews() = throw NotImplementedError("There is nothing to delete on remote.")

    override suspend fun deleteStory(storyId: Int) = throw NotImplementedError("There is nothing to delete on remote.")

    override suspend fun refreshNews() = TODO("What would refresh mean in this context?")

    override suspend fun refreshStory(storyId: Int) = throw NotImplementedError("There is nothing to refresh remotely.")

    override suspend fun saveStory(story: Story) = throw NotImplementedError("There is nowhere to save remotely.")
}