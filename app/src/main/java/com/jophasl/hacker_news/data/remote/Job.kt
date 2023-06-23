package com.jophasl.hacker_news.data.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Job(
    @JsonProperty("by") val `by`: String,
    @JsonProperty("id") val id: Int,
    @JsonProperty("score") val score: Int,
    @JsonProperty("text") val text: String,
    @JsonProperty("time") val time: Int,
    @JsonProperty("title") val title: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("url") val url: String
)