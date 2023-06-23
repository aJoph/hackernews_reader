package com.jophasl.hacker_news.data.remote

import android.util.Log
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsApi {
    @GET("item/{itemId}.json")
    suspend fun getStory(@Path("itemId") itemId: Int): Response<Item>

    @GET("item/{itemId}.json")
    suspend fun getComment(@Path("itemId") itemId: Int): Response<Item>

    @GET("item/{itemId}.json")
    suspend fun getAsk(@Path("itemId") itemId: Int): Response<Item>

    @GET("item/{itemId}.json")
    suspend fun getJob(@Path("itemId") itemId: Int): Response<Item>

    @GET("item/{itemId}.json")
    suspend fun getPoll(@Path("itemId") itemId: Int): Response<Item>

    @GET("item/{itemId}.json")
    suspend fun getPollPart(@Path("itemId") itemId: Int): Response<Item>

    @GET("user/{userName}.json")
    suspend fun getUser(@Path("userName") name: String): Response<Item>

    @GET("maxitem.json")
    suspend fun getMaxItem(): Response<Item>

    @GET("topstories.json")
    suspend fun getTopStoriesIds(): Response<List<Int>>

    @GET("newstories.json")
    suspend fun getNewStoriesIds(): Response<List<Int>>

    @GET("beststories.json")
    suspend fun getBestStoriesIds(): Response<List<Int>>

    @GET("askstories.json")
    suspend fun getAskStoriesIds(): Response<List<Int>>

    @GET("showstories.json")
    suspend fun getShowStoriesIds(): Response<List<Int>>

    @GET("jobstories.json")
    suspend fun getJobStoriesIds(): Response<List<Int>>
}

/// Fetches all /beststories. Note that it fetches all at the same time.
/// For performance reasons, prefer getBestStoriesIds.
suspend fun HackerNewsApi.getBestStories(): Response<List<Story>> {
    println("HE")
    val idsRes = this.getBestStoriesIds()
    if (!idsRes.isSuccessful || idsRes.body() == null) return Response.error(idsRes.code(), idsRes.errorBody()!!)
    val stories = mutableListOf<Story>()
    val listIds = idsRes.body()!!.forEach {
        val storyRes = this.getStory(it)
        if (!storyRes.isSuccessful || storyRes.body() == null) return Response.error(storyRes.code(), storyRes.errorBody()!!)
        val bod = storyRes.body()!!
        if (bod.type == "story") stories.add(bod.toSubtype<Story>())
    }
    return Response.success(stories)
}