package com.jophasl.hacker_news.data.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Item(
    @JsonProperty("id") val id: Int,
    @JsonProperty("deleted") val deleted: Boolean?,
    @JsonProperty("type") val type: String?,
    @JsonProperty("by") val `by`: String?,
    @JsonProperty("time") val time: Int?,
    @JsonProperty("text") val text: String?,
    @JsonProperty("dead") val dead: Boolean?,
    @JsonProperty("parent") val parent: Int?,
    @JsonProperty("poll") val poll: Int?,
    @JsonProperty("kids") val kids: List<Int>?,
    @JsonProperty("url") val url: String?,
    @JsonProperty("score") val score: Int?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("parts") val parts: List<Int>?,
    @JsonProperty("descendants") val descendants: Int?,
) {
    @Suppress("IMPLICIT_CAST_TO_ANY")
    fun <T> toSubtype(): T {

        val newObj = when (type) {
            "job" -> Job(
                by = by!!,
                score = score!!,
                id = id,
                text = text!!,
                title = title!!,
                time = time!!,
                type = type,
                url = url!!
            )
            "story" -> Story(
                by = by!!,
                descendants = descendants!!,
                id = id,
                kids = kids!!,
                score = score!!,
                time = time!!,
                title = title!!,
                type = type,
                url = url
            )
            "poll" -> Poll(
                by = by!!,
                descendants = descendants!!,
                id = id,
                kids = kids!!,
                parts =parts!!,
                score = score!!,
                text = text!!,
                time = time!!,
                title = title!!,
                type = type
            )
            "pollopt" -> PollPart(
                by = by!!,
                id = id,
                poll = poll!!,
                score = score!!,
                text = text!!,
                time = time!!,
                type = type,
            )
            else -> throw IllegalStateException("Found type other than \"job\", \"story\", \"comment\", \"poll\", or \"pollopt\"")
        }
        return newObj as T
    }
}