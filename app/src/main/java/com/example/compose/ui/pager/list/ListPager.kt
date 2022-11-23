package com.example.compose.ui.pager.list

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.di.ListPagerDomainActionEnterPoint
import com.example.compose.model.ListItemData
import dagger.hilt.EntryPoints

@Composable
fun PagerListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    listDomainAction: ListPagerDomainAction = getListPagerDomainAction()
) {
    val listState = rememberLazyListState()
    val data = viewModel.listData
    LaunchedEffect( true) {
        listDomainAction.init(viewModel)
    }
    Box (modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .align(Alignment.TopStart),
            verticalArrangement = Arrangement.Top,
            state = listState) {
            itemsIndexed(data) { index, item ->
                listItem(item) {

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