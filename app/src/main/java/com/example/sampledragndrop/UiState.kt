package com.example.sampledragndrop

interface UiState

data class DragAndDropUiState(val items: List<ItemData> = emptyList()) : UiState

interface DragAndDropActions {
    fun moveItem(from: Int, to: Int) {}

    companion object {
        fun default() = object : DragAndDropActions {}
    }
}