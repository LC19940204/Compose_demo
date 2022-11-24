package com.example.compose.ui.pager.subPage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubPageViewModel @Inject constructor(private val subPagerDomainAction: SubPagerDomainAction): ViewModel(){
    fun getDomainAction() = subPagerDomainAction
}