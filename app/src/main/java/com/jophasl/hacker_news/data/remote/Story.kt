package com.jophasl.hacker_news.data.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Story(
    @JsonProperty("by") var `by`: String,
    @JsonProperty("descendants") var descendants: Int,
    @JsonProperty("id") val id: Int,
    @JsonProperty("kids") val kids: List<Int>,
    @JsonProperty("score") val score: Int,
    @JsonProperty("time") val time: Int,
    @JsonProperty("title") val title: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("url") val url: String?
)