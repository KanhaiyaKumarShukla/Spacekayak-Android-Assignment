package com.example.spacekayakandroidassignment_kanhaiyakumar.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BottomSheetViewModel : ViewModel() {
    var isBottomSheetVisible by mutableStateOf(false)
        private set

    fun showBottomSheet() {
        isBottomSheetVisible = true
    }

    fun hideBottomSheet() {
        isBottomSheetVisible = false
    }
}