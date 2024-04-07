package com.example.newsapp.navigation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.SavedSearch
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.HomeScreen
import com.example.newsapp.detailscreens.HomeDetailScreen
import com.example.newsapp.fav.FavScreen
import com.example.newsapp.searchscreen.SearchScreen
import com.example.newsapp.worldnews.WorldNewsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination =Screen.Home.route) {
        composable(Screen.Home.route){
            HomeScreen(navController = navController)
        }

        composable(Screen.WorldNews.route){
            WorldNewsScreen(navController = navController)
        }
        composable(Screen.Favorite.route){
            FavScreen(navController = navController)
        }
        composable(Screen.Search.route){
            SearchScreen(navController = navController)
        }
        composable(Screen.DetailScreen.route+"/{image}/{tittle}/{des}/{newdes}/{date}/{about}/{write}",
            arguments = listOf(
                navArgument("image",){
                    type= NavType.StringType
                },
                navArgument("tittle",){
                    type= NavType.StringType
                },
                navArgument("des",){
                    type= NavType.StringType
                },
                navArgument("newdes",){
                    type= NavType.StringType
                },

                navArgument("date",){
                    type= NavType.StringType
                },

                navArgument("about",){
                    type= NavType.StringType
                },

                navArgument("write",){
                    type= NavType.StringType
                },
            )

            ){
            val image=it.arguments?.getString("image")
            val tittle=it.arguments?.getString("tittle")
            val des=it.arguments?.getString("des")
            val newdes=it.arguments?.getString("newdes")
            val date=it.arguments?.getString("date")
            val about=it.arguments?.getString("about")
            val write=it.arguments?.getString("write")
            HomeDetailScreen(navController,image,tittle,des,newdes,date,about,write)
        }
    }
}



sealed class Screen(
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    object WorldNews : Screen(
        "WorldNews",

        selectedIcon = Icons.Filled.Newspaper,
        unSelectedIcon = Icons.Outlined.Newspaper
    )

    object DetailScreen : Screen(
        "DetailScreen",

        selectedIcon = Icons.Filled.Newspaper,
        unSelectedIcon = Icons.Outlined.Newspaper
    )

    object Home : Screen(
        "Home",

        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )


    object Favorite : Screen(
        "Favorite",

        selectedIcon = Icons.Filled.Favorite,
        unSelectedIcon = Icons.Outlined.FavoriteBorder
    )

    object Search : Screen(
        "Search",

        selectedIcon = Icons.Filled.SavedSearch,
        unSelectedIcon = Icons.Outlined.Search
    )
}







@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Entry() {
    val navController= rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation(navController = navController)
    }) {
        Navigation(navController = navController)
    }
}
@Composable
fun BottomNavigation(navController: NavController) {
    val item= listOf(
        Screen.Home,
        Screen.WorldNews,
        Screen.Search,
        Screen.Favorite
    )

    NavigationBar {
        val NavStack by navController.currentBackStackEntryAsState()
        val current=NavStack?.destination?.route
        item.forEach {
            NavigationBarItem(selected = current==it.route, onClick ={
                navController.navigate(it.route) {
                    navController.graph?.let {
                        it.route?.let { it1 -> popUpTo(it1) }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = {
                Icon(imageVector = if (current==it.route)it.selectedIcon else it.unSelectedIcon, contentDescription = "")
            },
                label = {
                    Text(text = it.route)
                }

                )
        }
    }
}
