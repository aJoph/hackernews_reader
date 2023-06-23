package com.jophasl.hacker_news.data.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Comment(
    @JsonProperty("by") val `by`: String,
    @JsonProperty("id") val id: Int,
    @JsonProperty("kids") val kids: List<Int>,
    @JsonProperty("parent") val parent: Int,
    @JsonProperty("text") val text: String,
    @JsonProperty("time") val time: Int,
    @JsonProperty("type") val type: String
)