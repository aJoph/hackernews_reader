package com.jophasl.hacker_news.data.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Ask(
    @JsonProperty("by") val `by`: String,
    @JsonProperty("descendants") val descendants: Int,
    @JsonProperty("id") val id: Int,
    @JsonProperty("kids") val kids: List<Int>,
    @JsonProperty("score") val score: Int,
    @JsonProperty("text") val text: String,
    @JsonProperty("time") val time: Int,
    @JsonProperty("title") val title: String,
    @JsonProperty("type") val type: String
)