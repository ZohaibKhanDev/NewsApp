package com.example.newsapp.fav

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Splitscreen
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.newsapp.newsapi.MainViewModel
import com.example.newsapp.newsapi.Repository
import com.example.newsapp.newsapi.ResultState
import com.example.newsapp.roomdatabase.DataBase
import com.example.newsapp.roomdatabase.FavItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedContentLambdaTargetStateParameter")
@Composable
fun FavScreen(navController: NavController) {

    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        DataBase::class.java,
        "demo.db"
    ).allowMainThreadQueries().build()
    val repository = remember {
        Repository(db)
    }
    val viewModel = remember {
        MainViewModel(repository)
    }
    var favData by remember {
        mutableStateOf<List<FavItem>?>(null)
    }

    var isFav by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = isFav) {
        viewModel.getAllFav()
    }
    val favState by viewModel.allFav.collectAsState()
    val list by remember {
        mutableStateOf(false)
    }
    when (favState) {
        is ResultState.Error -> {
            val error = (favState as ResultState.Error).error
            isFav = false
            Text(text = error.toString())
        }

        is ResultState.Loading -> {
            isFav = true
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ResultState.Success -> {
            val success = (favState as ResultState.Success).response
            isFav = false
            favData = success
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Favourites", modifier = Modifier.padding(start = 10.dp))
            },
                navigationIcon = {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                },
                actions = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                }
            )
        }
    ) {
        if (isFav) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding(), start = 12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Dashboard, contentDescription = "")
            Icon(imageVector = Icons.Default.FormatListBulleted, contentDescription = "")
        }

        if (list) {
            AnimatedContent(targetState = list) {
                LazyColumn {
                    favData?.let { fav ->
                        fav?.let {
                            items(it) {

                            }
                        }
                    }
                }
            }
        }


    }

}