package com.example.compose.ui.pager.subPage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun subPage(
    navHostController: NavHostController,
    viewModel: SubPageViewModel =  hiltViewModel(),
    backgroundColor: List<Color>? = null,
    pagerTitle: String
) {
    Log.e("Test", "subPage viewModel: $viewModel")
    val domainAction = viewModel.getDomainAction()
    Column(modifier = Modifier.fillMaxSize().run {
        backgroundColor?.let {
            this.background(brush = Brush.radialGradient(it))
        } ?: kotlin.run { this }
    }, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(modifier = Modifier.clickable { navHostController.popBackStack() }, text = pagerTitle)
    }

}