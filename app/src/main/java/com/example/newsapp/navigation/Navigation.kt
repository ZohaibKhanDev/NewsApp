package com.example.newsapp.navigation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Person
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
import com.example.newsapp.detailscreens.DetailScreen

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
        composable(Screen.Profile.route){
            ProfileScreen(navController = navController)
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
                    type= NavType.IntType
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
            val date=it.arguments?.getInt("date")
            val about=it.arguments?.getString("about")
            val write=it.arguments?.getString("write")
            DetailScreen(navController,image,tittle,des,newdes,date,about,write)
        }
    }
}

sealed class Screen(
    val route: String,
   val tittle: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    object WorldNews : Screen(
        "WorldNews",
        "WorldNews",
        selectedIcon = Icons.Filled.Newspaper,
        unSelectedIcon = Icons.Outlined.Newspaper
    )

    object DetailScreen : Screen(
        "DetailScreen",
        "DetailScreen",
        selectedIcon = Icons.Filled.Newspaper,
        unSelectedIcon = Icons.Outlined.Newspaper
    )

    object Home : Screen(
        "Home",
        "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )


    object Favorite : Screen(
        "Favorite",
        "Favorite",
        selectedIcon = Icons.Filled.Favorite,
        unSelectedIcon = Icons.Outlined.FavoriteBorder
    )

    object Profile : Screen(
        "Profile",
        "Profile",
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person
    )
}



@Composable
fun WorldNewsScreen(navController: NavController) {

}

@Composable
fun FavScreen(navController: NavController) {

}

@Composable
fun ProfileScreen(navController: NavController) {

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
        Screen.Favorite,
        Screen.Profile
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
