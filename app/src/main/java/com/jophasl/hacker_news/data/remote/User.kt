package com.jophasl.hacker_news.data.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("about") val about: String,
    @JsonProperty("created") val created: Int,
    @JsonProperty("delay") val delay: Int,
    @JsonProperty("id") val id: String,
    @JsonProperty("karma") val karma: Int,
    @JsonProperty("submitted") val submitted: List<Int>
)