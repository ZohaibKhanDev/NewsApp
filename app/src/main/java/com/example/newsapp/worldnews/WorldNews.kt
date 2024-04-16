package com.example.newsapp.worldnews

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.newsapp.navigation.Screen
import com.example.newsapp.newsapi.MainViewModels
import com.example.newsapp.newsapi.Repository
import com.example.newsapp.newsapi.ResultState
import com.example.newsapp.roomdatabase.DataBase
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WorldNewsScreen(navController: NavController) {

    val viewModel:MainViewModels= koinInject()


    var worlddata by remember {
        mutableStateOf<World?>(null)
    }
    var isWorld by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllWorldNews()
    }
    val worldstate by viewModel.allWorldNews.collectAsState()
    when (worldstate) {
        is ResultState.Error -> {
            val error = (worldstate as ResultState.Error).error
            isWorld=false
            Text(text = error.toString())
        }

        is ResultState.Loading -> {
            isWorld = true

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ResultState.Success -> {
            val success = (worldstate as ResultState.Success).response
            isWorld=false
            worlddata = success
        }
    }


    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "World News",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.ExtraBold
            )
        }, navigationIcon = {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "", modifier = Modifier.size(30.dp))
        })
    }) {
        if (isWorld){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(top = it.calculateTopPadding(), start = 10.dp, end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            worlddata?.let {news->
                items(news.results) { home ->
                    WorldNewsItem(result = home,navController)
                }
            }
        }
        
    }

}

@Composable
fun WorldNewsItem(result: Result, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    Screen.DetailScreen.route +
                    "/${Uri.encode(result.multimedia[0].url)}/${result.title}/${result.abstract}/${result.itemType}/${result.updatedDate}/${result.section}/${result.byline}"
                )
            }
            .height(400.dp)
            .padding(10.dp), colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val image: Resource<Painter> = asyncPainterResource(data = result.multimedia[0].url)
            KamelImage(
                resource = image,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = result.title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(all = 10.dp)
                    .background(Color.LightGray.copy(alpha = 0.25f)),
                color = Color.White
            )



            Text(
                text = result.byline,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(10.dp)
                    .background(Color.LightGray.copy(alpha = 0.15f)),
                color = Color.White
            )

        }


    }
}
