package com.example.compose.ui.pager.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.pager.list.PagerListScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun homePage(
    modifier: Modifier = Modifier
) {

    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val horizontalPageState = rememberPagerState(2)
    val scope =  rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "HomePage")}, navigationIcon = { IconButton(
                onClick = { /*TODO*/ }) {
                 Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back" )
            }}, actions = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "home")
            })
        },
        bottomBar = {
            NavigationBar {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { /*navController.navigate("Pager1")*/
                        navController.navigate("NavPager1")
                       }) {
                        Text(text = "Page1")
                    }
                    IconButton(onClick = { /*navController.navigate("Pager2")*/
                        navController.navigate("NavPager2")}) {
                        Text(text = "Page2")
                    }
                    IconButton(onClick = {/*navController.navigate("Pager3")*/
                        navController.navigate("NavPager3")}) {
                        Text(text = "Page3")
                    }
                }
            }
        }
    )
    {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center) {
            oneHomePage(navController = navController)

            /*HorizontalPager(3, state = horizontalPageState) {
                if (horizontalPageState.currentPage == 0) {
                    oneHomePage(navController = navController)
                } else {
                    homeSubPage(pagerTitle = "Pager${this.currentPage}")
                }
            }*/
        }
    }
}



@Composable
fun homeSubPage(
    pagerTitle: String
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = pagerTitle)
    }

}

@Composable
fun oneHomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "NavPager1") {
        composable(route = "NavPager1") {
            PagerListScreen()
        }
        composable(route = "NavPager2") {
            homeSubPage(pagerTitle = "NavPager2")
        }
        composable(route = "NavPager3") {
            homeSubPage(pagerTitle = "NavPager3")
        }
    }
}


