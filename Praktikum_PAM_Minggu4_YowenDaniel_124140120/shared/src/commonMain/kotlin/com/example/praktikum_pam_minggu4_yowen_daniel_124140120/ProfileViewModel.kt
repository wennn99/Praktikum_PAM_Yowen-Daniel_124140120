package com.example.praktikum_pam_minggu4_yowen_daniel_124140120

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProfileUiState(
    val name: String = "Yowen Daniel",
    val nim: String = "124140120",
    val bio: String = "Mahasiswa semester 5 Teknik Informatika yang berkuliah di Institut Teknologi Sumatera.",
    val email: String = "yowen.124140120@student.itera.ac.id",
    val phone: String = "089661511300",
    val location: String = "Lampung Selatan",
    val isDarkMode: Boolean = true,
    val isEditing: Boolean = false,
    val draftName: String = name,
    val draftBio: String = bio,
)

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun setDarkMode(enabled: Boolean) {
        _uiState.update { it.copy(isDarkMode = enabled) }
    }

    fun startEditing() {
        _uiState.update { it.copy(isEditing = true, draftName = it.name, draftBio = it.bio) }
    }

    fun updateDraftName(name: String) {
        _uiState.update { it.copy(draftName = name) }
    }

    fun updateDraftBio(bio: String) {
        _uiState.update { it.copy(draftBio = bio) }
    }

    fun cancelEditing() {
        _uiState.update { it.copy(isEditing = false, draftName = it.name, draftBio = it.bio) }
    }

    fun saveProfile() {
        _uiState.update {
            it.copy(
                name = it.draftName.trim().ifBlank { it.name },
                bio = it.draftBio.trim().ifBlank { it.bio },
                isEditing = false,
            )
        }
    }
}