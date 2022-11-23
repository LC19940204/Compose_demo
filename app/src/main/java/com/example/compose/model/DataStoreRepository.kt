package com.example.compose.model

import javax.inject.Inject

class DataStoreRepository @Inject constructor(): DataStore {
    override fun getData(): List<ListItemData> {
        return mutableListOf<ListItemData>().apply {
            for (i in 0..100) {
                add(ListItemData("Item $i"))
            }
        }
    }
}