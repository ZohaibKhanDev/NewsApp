package com.example.newsapp.fav

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.newsapp.navigation.Screen
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
    var list by remember {
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


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(top = 50.dp, bottom = 50.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalArrangement = Arrangement.Center,

            ) {
            favData?.let { fav ->
                fav.let {
                    items(it) { hm ->
                        FavItem(favItem = hm, navController)
                    }
                }

            }
        }
    }
}


@Composable
fun FavItem(favItem: FavItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    Screen.FavDetail.route +"/${Uri.encode(favItem.image)}/${favItem.tittle}/${favItem.des}"
                )
            }
            .height(300.dp)
            .padding(10.dp), elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            AsyncImage(
                model = favItem.image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        }



    }
}

@Composable
fun FavDetail(navController: NavController, image: String?, tittle: String?, des: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(899.dp),
                contentScale = ContentScale.Crop
            )
            Card(
                modifier = Modifier

                    .width(311.dp)
                    .height(141.dp)
                    .align(Alignment.Center),
                colors = CardDefaults.cardColors(Color.LightGray.copy(alpha = 0.90f))
            ) {
                Text(
                    text = "$tittle",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "$des",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                )

            }
        }

    }
    Box(modifier = Modifier
        .clip(RoundedCornerShape(1.dp))
        .padding(start = 10.dp, top = 8.dp)
        .width(32.dp)
        .height(32.dp)
        .clickable { navController.popBackStack() }
        .background(Color(0XFFF5F5F5).copy(alpha = 0.90f)), contentAlignment = Alignment.Center) {
        Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = "")
    }
}
