package com.giraffe.rickmortyapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giraffe.rickmortyapp.domain.usecases.GetDetailedCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getDetailedCharacterUseCase: GetDetailedCharacterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()


    fun getDetailedCharacter(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    detailedCharacterEntity = getDetailedCharacterUseCase(id),
                    isLoading = false
                )
            }
        }
    }
}