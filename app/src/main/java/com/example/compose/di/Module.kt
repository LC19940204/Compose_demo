package com.example.compose.di

import com.example.compose.model.DataStore
import com.example.compose.model.DataStoreRepository
import com.example.compose.ui.pager.list.ListPagerDomainAction
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@EntryPoint
@InstallIn(ActivityComponent::class)
interface ListPagerDomainActionEnterPoint{
    fun listPagerDomainAction(): ListPagerDomainAction
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SingletonModule {

    @Singleton
    @Binds
    abstract fun bindDataStore(
        dataStoreRepository: DataStoreRepository
    ): DataStore
}