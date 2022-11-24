package com.example.compose.ui.pager.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.ui.pager.list.PagerListScreen
import com.example.compose.ui.pager.subPage.subPage
import com.example.compose.ui.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun homePage(
    modifier: Modifier = Modifier
) {

    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
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
        }
    }
}


@Composable
fun oneHomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Log.e("Test", "oneHomePage1")
    NavHost(navController = navController, startDestination = "NavPager1") {
        Log.e("Test", "oneHomePage2")
        composable(route = "NavPager1") {
            Log.e("Test", "oneHomePage NavPager1")
            PagerListScreen(navController)
        }
        composable(route = "NavPager2") {
            subPage(backgroundColor = AppTheme.colors.gradient2_3, pagerTitle = "NavPager2", navHostController = navController)
        }
        composable(route = "NavPager3") {
            subPage(backgroundColor = AppTheme.colors.gradient3_2,pagerTitle = "NavPager3", navHostController = navController)
        }

        composable(route = "ListDetailPage/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType })) {
            subPage(pagerTitle = it.arguments?.getString("title")?:"ListDetailPage", navHostController = navController)
        }
    }
}


