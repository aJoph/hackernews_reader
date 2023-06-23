package com.jophasl.hacker_news.data.remote

import com.fasterxml.jackson.annotation.JsonProperty
import retrofit2.Response

data class PollPart(
    @JsonProperty("by") val `by`: String,
    @JsonProperty("id") val id: Int,
    @JsonProperty("poll") val poll: Int,
    @JsonProperty("score") val score: Int,
    @JsonProperty("text") val text: String,
    @JsonProperty("time") val time: Int,
    @JsonProperty("type") val type: String
) {
    suspend fun getParent(api: HackerNewsApi): Response<Item> {
        return api.getPoll(poll)
    }
}