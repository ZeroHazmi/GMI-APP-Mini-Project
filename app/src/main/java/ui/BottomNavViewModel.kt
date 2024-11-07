package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BottomNavViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(BottomNavUiState())
    val uiState: StateFlow<BottomNavUiState> = _uiState

    fun updateCurrentPage(page: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(currentPage = page)
        }
    }
}

data class BottomNavUiState(
    val currentPage: String = "home"
)