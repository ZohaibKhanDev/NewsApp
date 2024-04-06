package com.example.newsapp.worldnews

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.HomeNews
import com.example.newsapp.navigation.Screen
import com.example.newsapp.newsapi.MainViewModel
import com.example.newsapp.newsapi.News
import com.example.newsapp.newsapi.Repository
import com.example.newsapp.newsapi.Result
import com.example.newsapp.newsapi.ResultState
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WorldNewsScreen(navController: NavController) {

    val repository = remember {
        Repository()
    }
    val viewModel = remember {
        MainViewModel(repository)
    }

    var worlddata by remember {
        mutableStateOf<News?>(null)
    }
    var isWorld by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = isWorld) {
        viewModel.getAllWorldNews()
    }
    val worldstate by viewModel.allWorldNews.collectAsState()
    when (worldstate) {
        is ResultState.Error -> {
            val error = (worldstate as ResultState.Error).error
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
            worlddata = success
        }
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "World News",
                color = Color.Red,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
        }, navigationIcon = {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "")
        })
    }) {


        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(top = it.calculateTopPadding(), start = 10.dp, end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            worlddata?.let {
                worlddata?.let { result ->
                    items(result.results) { home ->
                        WorldNewsItem(result = home)
                    }
                }
            }
        }
    }

}

@Composable
fun WorldNewsItem(result: Result) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(10.dp), colors = CardDefaults.cardColors(Color.LightGray), elevation = CardDefaults.cardElevation(5.dp)
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
