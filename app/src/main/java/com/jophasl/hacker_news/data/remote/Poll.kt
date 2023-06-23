package com.jophasl.hacker_news.data.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Poll(
    @JsonProperty("by") val `by`: String,
    @JsonProperty("descendants") val descendants: Int,
    @JsonProperty("id") val id: Int,
    @JsonProperty("kids") val kids: List<Int>,
    @JsonProperty("parts") val parts: List<Int>,
    @JsonProperty("score") val score: Int,
    @JsonProperty("text") val text: String,
    @JsonProperty("time") val time: Int,
    @JsonProperty("title") val title: String,
    @JsonProperty("type") val type: String
) {
    suspend fun getParts(api: HackerNewsApi): List<PollPart> {
        val list = mutableListOf<PollPart>()
        for (id in parts) {
            val pollPart = api.getPollPart(id).body() ?: throw NullPointerException("Failed to retrive one of ${list.size} poll parts.")
            list.add(pollPart.toSubtype())
        }
        return list
    }
}