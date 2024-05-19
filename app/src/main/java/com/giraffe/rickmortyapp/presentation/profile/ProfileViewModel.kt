package com.giraffe.rickmortyapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giraffe.rickmortyapp.domain.usecases.GetCharacterInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()


    fun getCharacterInfo(id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    characterInfoEntity = getCharacterInfoUseCase(id),
                    isLoading = false
                )
            }
        }
    }
}