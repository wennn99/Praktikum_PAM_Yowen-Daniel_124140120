package com.example.praktikum_pam_minggu4_yowen_daniel_124140120

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SharedCommonTest {

    @Test
    fun example() {
        assertEquals(3, 1 + 2)
    }

    @Test
    fun saveProfileUpdatesNameAndBio() {
        val viewModel = ProfileViewModel()

        viewModel.startEditing()
        viewModel.updateDraftName("Yowen Daniel Baru")
        viewModel.updateDraftBio("Bio terbaru")
        viewModel.saveProfile()

        assertEquals("Yowen Daniel Baru", viewModel.uiState.value.name)
        assertEquals("Bio terbaru", viewModel.uiState.value.bio)
        assertFalse(viewModel.uiState.value.isEditing)
    }

    @Test
    fun cancelEditingDiscardsDraftChanges() {
        val viewModel = ProfileViewModel()

        viewModel.startEditing()
        viewModel.updateDraftName("Nama sementara")
        viewModel.cancelEditing()

        assertEquals("Yowen Daniel", viewModel.uiState.value.name)
        assertEquals("Yowen Daniel", viewModel.uiState.value.draftName)
        assertFalse(viewModel.uiState.value.isEditing)
    }

    @Test
    fun darkModeIsStoredInUiState() {
        val viewModel = ProfileViewModel()

        viewModel.setDarkMode(false)

        assertTrue(!viewModel.uiState.value.isDarkMode)
    }
}