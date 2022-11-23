package com.example.compose.model

interface DataStore {
    fun getData(): List<ListItemData>
}


data class ListItemData (
    val title: String
)

