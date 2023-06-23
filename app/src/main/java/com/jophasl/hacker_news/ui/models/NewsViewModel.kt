package com.jophasl.hacker_news.ui.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jophasl.hacker_news.data.HackerNewsRepository
import com.jophasl.hacker_news.data.remote.Story
import com.jophasl.hacker_news.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: HackerNewsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsUiState())

    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    var newsList = MutableStateFlow<List<Story>>(emptyList())
        private set
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        populateFrontpage()
    }

    fun populateFrontpage() {
        viewModelScope.launch {
            when (val result = newsRepository.getFrontPage()) {
                is Resource.Success -> {
                    loadError.value = ""
                    isLoading.value = false
                    newsList.value = result.data!!
                }
                is Resource.Error -> {
                    loadError.value = result.message ?: "Unexpected Error"
                    isLoading.value = false
                }
            }
        }
    }
}