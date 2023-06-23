package com.jophasl.hacker_news.data

import com.jophasl.hacker_news.data.remote.HackerNewsApi
import com.jophasl.hacker_news.data.remote.Story
import com.jophasl.hacker_news.data.remote.getBestStories
import com.jophasl.hacker_news.util.Resource

class HackerNewsRepository(
    private val api: HackerNewsApi
) {
    /// Fetches entire list of best stories, but only converts those that match the
    /// page and size.
    suspend fun getBestStories(page: Int, size: Int): Resource<List<Story>> {
        val response = try {
            api.getBestStoriesIds()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "Unexpected error fetching best stories ids")
        }
        if (!response.isSuccessful || response.body() == null) return Resource.Error("Failed to fetch /beststories")
        val ids = response.body()!!.subList(page * size, (page + 1) * size)
        println("Page: $page, Size: $size, sublist(${page * size}, ${(page + 1) * size})")
        val stories = mutableListOf<Story>()
        ids.forEach() {
            val storyRes = api.getStory(it)
            if (!storyRes.isSuccessful || storyRes.body() == null) return Resource.Error("Error fetching story body from Id")
            val story = storyRes.body()!!
            if (story.type == "story") stories.add(story.toSubtype())
        }
        return Resource.Success(stories)
    }

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

