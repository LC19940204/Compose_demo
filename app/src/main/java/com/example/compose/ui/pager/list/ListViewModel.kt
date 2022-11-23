package com.example.compose.ui.pager.list

import androidx.compose.runtime.mutableStateListOf

import androidx.lifecycle.ViewModel
import com.example.compose.model.ListItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel@Inject constructor(): ViewModel() {
    val listData =  mutableStateListOf<ListItemData>()
}


data class ListData(
    val title: String = "",
    val description: String = "",
    val image: String ="",
    var data: List<ListItemData> = listOf(ListItemData("test"), ListItemData("test1"))
)
