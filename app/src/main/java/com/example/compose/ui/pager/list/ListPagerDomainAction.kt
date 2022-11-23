package com.example.compose.ui.pager.list

import com.example.compose.model.DataStore
import com.example.compose.model.ListItemData
import javax.inject.Inject

class ListPagerDomainAction @Inject constructor(private val dataStore: DataStore) {

    fun init(viewModel: ListViewModel) {
        viewModel.listData.addAll(dataStore.getData())
    }

    fun deleteItem(viewModel: ListViewModel, item: ListItemData) {
        viewModel.listData.remove(item)
    }
}