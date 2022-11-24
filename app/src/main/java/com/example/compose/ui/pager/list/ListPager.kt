package com.example.compose.ui.pager.list

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.compose.di.ListPagerDomainActionEnterPoint
import com.example.compose.model.ListItemData
import com.example.compose.ui.theme.AppTheme
import dagger.hilt.EntryPoints

@Composable
fun PagerListScreen(
    navController: NavHostController,
    viewModel: ListViewModel = hiltViewModel(),
    listDomainAction: ListPagerDomainAction = getListPagerDomainAction()
) {
    Log.e("Test", "PagerListScreen viewModel: $viewModel")
    val data = viewModel.listData
    LaunchedEffect(null) {
        Log.e("Test", "PagerListScreen viewModel: LaunchedEffect")
        listDomainAction.init(viewModel)
    }
    Box (modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier
            .background(brush = Brush.horizontalGradient(AppTheme.colors.gradient6_1))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .align(Alignment.TopStart),
            verticalArrangement = Arrangement.Top) {
            Log.e("Test", "PagerListScreen viewModel: LazyColumn")
            itemsIndexed(data) { index, item ->
                listItem(item) {
                   if (index >= 10) {
                       navController.navigate("ListDetailPage/${it.title}")
                   }
                }
            }
        }
    }
}

@Composable
fun listItem(item: ListItemData, itemClicked: (ListItemData) -> Unit = {}) {
    var itemClick = remember {
         mutableStateOf(false)
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable {
            itemClick.value = itemClick.value.not()
            itemClicked(item)
        }, verticalArrangement = Arrangement.Center) {
        Text(text = item.title, modifier = Modifier
            .padding(16.dp)
            .height(
                animateDpAsState(targetValue = if (itemClick.value.not()) 20.dp else 50.dp).value
            ))
    }
}


@Composable
fun getListPagerDomainAction(): ListPagerDomainAction {
    return EntryPoints.get(
        LocalContext.current,
        ListPagerDomainActionEnterPoint::class.java
    ).listPagerDomainAction()
}