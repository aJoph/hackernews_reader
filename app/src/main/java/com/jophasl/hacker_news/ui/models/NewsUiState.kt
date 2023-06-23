package com.jophasl.hacker_news.ui.models

enum class StoryType {
    BEST,
    NEW,
    TOP
}

data class NewsUiState(
    val storyType: StoryType = StoryType.BEST
)
