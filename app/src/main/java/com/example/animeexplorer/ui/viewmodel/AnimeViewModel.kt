package com.example.animeexplorer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeexplorer.data.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeViewModel(
    private val repository: AnimeRepository = AnimeRepository()
) : ViewModel() {

    private val _homeUiState = MutableStateFlow<AnimeUiState>(AnimeUiState.Loading)
    val homeUiState: StateFlow<AnimeUiState> = _homeUiState.asStateFlow()

    private val _detailUiState = MutableStateFlow<AnimeDetailUiState>(AnimeDetailUiState.Loading)
    val detailUiState: StateFlow<AnimeDetailUiState> = _detailUiState.asStateFlow()

    init {
        getAnimeList()
    }

    fun getAnimeList() {
        viewModelScope.launch {
            _homeUiState.value = AnimeUiState.Loading
            try {
                val list = repository.getAnimeList()
                _homeUiState.value = AnimeUiState.Success(list)
            } catch (e: Exception) {
                _homeUiState.value = AnimeUiState.Error(
                    e.localizedMessage ?: "Terjadi kesalahan saat memuat data anime."
                )
            }
        }
    }

    fun getAnimeDetail(id: String) {
        viewModelScope.launch {
            _detailUiState.value = AnimeDetailUiState.Loading
            try {
                val detail = repository.getAnimeDetail(id)
                _detailUiState.value = AnimeDetailUiState.Success(detail)
            } catch (e: Exception) {
                _detailUiState.value = AnimeDetailUiState.Error(
                    e.localizedMessage ?: "Terjadi kesalahan saat memuat detail anime."
                )
            }
        }
    }
}
