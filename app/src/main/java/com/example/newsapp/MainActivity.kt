package com.example.newsapp

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.navigation.Entry
import com.example.newsapp.navigation.Screen
import com.example.newsapp.newsapi.MainViewModel
import com.example.newsapp.newsapi.News
import com.example.newsapp.newsapi.Repository
import com.example.newsapp.newsapi.Result
import com.example.newsapp.newsapi.ResultState
import com.example.newsapp.ui.theme.NewsAppTheme
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Entry()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val repository = remember {
        Repository()
    }
    val viewModel = remember {
        MainViewModel(repository)
    }

    var newsdata by remember {
        mutableStateOf<News?>(null)
    }
    val isNews by remember {
        mutableStateOf(false)
    }
    var healthy by remember {
        mutableStateOf(false)
    }
    var Tecnoligy by remember {
        mutableStateOf(false)
    }
    var Politices by remember {
        mutableStateOf(false)
    }
    var Art by remember {
        mutableStateOf(false)
    }
    var Sports by remember {
        mutableStateOf(false)
    }
    var SundayReview by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = isNews) {
        viewModel.getAllNews()
        if (healthy) {
            viewModel.getAllHealthy()

        }
        if (Tecnoligy) {
            viewModel.getAllTechnlogy()
        }
        if (Politices) {
            viewModel.getAllPolitics()
        }
        if (Art) {
            viewModel.getAllArt()
        }

        if (Sports) {
            viewModel.getAllSundayReview()
        }

        if (SundayReview) {
            viewModel.getAllSundayReview()
        }
    }

    val state by viewModel.allNews.collectAsState()
    when (state) {
        is ResultState.Loading -> {
            CircularProgressIndicator()
        }

        is ResultState.Success -> {
            val response = (state as ResultState.Success).response
            newsdata = response
        }

        is ResultState.Error -> {
            val error = (state as ResultState.Error).error
            Text(text = error.toString())
        }
    }

    var helthydata by remember {
        mutableStateOf<News?>(null)
    }
    var tecdata by remember {
        mutableStateOf<News?>(null)
    }
    var politicesdata by remember {
        mutableStateOf<News?>(null)
    }
    var artdata by remember {
        mutableStateOf<News?>(null)
    }
    var sportsdata by remember {
        mutableStateOf<News?>(null)
    }
    var sundaydata by remember {
        mutableStateOf<News?>(null)
    }


    val healthyState by viewModel.allHealthy.collectAsState()
    when (healthyState) {
        is ResultState.Loading -> {
            CircularProgressIndicator()
        }

        is ResultState.Success -> {
            val response = (healthyState as ResultState.Success).response
            newsdata = response
        }

        is ResultState.Error -> {
            val error = (healthyState as ResultState.Error).error
            Text(text = error.toString())
        }
    }
    val sundayState by viewModel.allSundayReview.collectAsState()
    when (sundayState) {
        is ResultState.Loading -> {
            CircularProgressIndicator()
        }

        is ResultState.Success -> {
            val response = (sundayState as ResultState.Success).response
            newsdata = response
        }

        is ResultState.Error -> {
            val error = (sundayState as ResultState.Error).error
            Text(text = error.toString())
        }
    }

    val tecState by viewModel.allTechnlogy.collectAsState()
    when (tecState) {
        is ResultState.Loading -> {
            CircularProgressIndicator()
        }

        is ResultState.Success -> {
            val response = (tecState as ResultState.Success).response
            newsdata = response
        }

        is ResultState.Error -> {
            val error = (tecState as ResultState.Error).error
            Text(text = error.toString())
        }
    }

    val politicsState by viewModel.allPolitics.collectAsState()
    when (politicsState) {
        is ResultState.Loading -> {
            CircularProgressIndicator()
        }

        is ResultState.Success -> {
            val response = (politicsState as ResultState.Success).response
            newsdata = response
        }

        is ResultState.Error -> {
            val error = (politicsState as ResultState.Error).error
            Text(text = error.toString())
        }
    }

    val artState by viewModel.allArt.collectAsState()
    when (artState) {
        is ResultState.Loading -> {
            CircularProgressIndicator()
        }

        is ResultState.Success -> {
            val response = (artState as ResultState.Success).response
            newsdata = response
        }

        is ResultState.Error -> {
            val error = (artState as ResultState.Error).error
            Text(text = error.toString())
        }
    }

    val sportsState by viewModel.allSports.collectAsState()
    when (sportsState) {
        is ResultState.Loading -> {
            CircularProgressIndicator()
        }

        is ResultState.Success -> {
            val response = (sportsState as ResultState.Success).response
            newsdata = response
        }

        is ResultState.Error -> {
            val error = (sportsState as ResultState.Error).error
            Text(text = error.toString())
        }
    }

    var topbar by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            OutlinedTextField(
                value = topbar,
                onValueChange = {
                    topbar = it
                },
                placeholder = {
                    Text(
                        text = "Regecoin to the moon",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    )
                },
                shape = RoundedCornerShape(300.dp),
                modifier = Modifier
                    .width(336.dp)
                    .height(65.dp)
                    .padding(top = 11.dp),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                },
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    textAlign = TextAlign.Justify,

                    )

            )
        })
    }) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding()),

            ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Latest News",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 10.dp, start = 14.dp)
                )

                /* Icon(
                     imageVector = Icons.Default.ArrowBack,
                     contentDescription = "",
                     modifier = Modifier
                         .padding(top = 10.dp, start = 14.dp)
                         .rotate(180f)
                 )*/
            }
            LazyRow(
                modifier = Modifier
                    .width(396.dp)
                    .height(240.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                newsdata?.let { result ->
                    items(result.results) { home ->
                        HomeNews(result = home, navController)
                    }
                }
            }

            LazyRow(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                item {
                    Button(
                        onClick = {
                            healthy = true
                            viewModel.getAllHealthy()
                        },
                        colors = ButtonDefaults.buttonColors(Color(0XFFFF3A44)),
                    ) {
                        Text(text = "Healthy")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = {
                            Tecnoligy = true
                            viewModel.getAllTechnlogy()
                        },
                        colors = ButtonDefaults.buttonColors(Color(0XFFFF3A44)),
                    ) {
                        Text(text = "Technlogy")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = {
                            Politices = true
                            viewModel.getAllPolitics()
                        },
                        colors = ButtonDefaults.buttonColors(Color(0XFFFF3A44)),
                    ) {
                        Text(text = "Politics")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = {
                            Art = true
                            viewModel.getAllArt()
                        },
                        colors = ButtonDefaults.buttonColors(Color(0XFFFF3A44)),
                    ) {
                        Text(text = "Art")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = {
                            Sports = true
                            viewModel.getAllSports()
                        },
                        colors = ButtonDefaults.buttonColors(Color(0XFFFF3A44)),
                    ) {
                        Text(text = "Sports")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = {
                            SundayReview = true
                            viewModel.getAllHealthy()
                        },
                        colors = ButtonDefaults.buttonColors(Color(0XFFFF3A44)),
                    ) {
                        Text(text = "SundayReview")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                newsdata?.let { result ->
                    items(result.results) {
                        Catagery1(result = it)
                    }

                }


            }

        }
    }
}

@Composable
fun Catagery1(result: Result) {
    Card(
        modifier = Modifier
            .width(345.dp)
            .height(228.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            val image: Resource<Painter> = asyncPainterResource(data = result.multimedia[0].url)
            KamelImage(
                resource = image,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.LightGray.copy(alpha = 0.35f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = result.title,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier

                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(top = 7.dp, start = 6.dp, end = 6.dp),
                    color = Color.White,

                    )
            }
            Text(
                text = result.byline,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                color = Color.White,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold
            )

        }
    }
}


@Composable
fun HomeNews(result: Result, navController: NavController) {

    Card(modifier = Modifier
        .clickable {
            navController.navigate(
                Screen.DetailScreen.route +
                        "/${Uri.encode(result.multimedia[0].url)}/${result.multimedia[0].caption}/${result.abstract}/${result.byline}/${result.publishedDate}/${result.desFacet}/${result.itemType}"
            )
        }
        .width(396.dp)
        .height(240.dp)
        .padding(0.dp),
        elevation = CardDefaults.cardElevation(4.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            val image: Resource<Painter> = asyncPainterResource(data = result.multimedia[0].url)
            KamelImage(
                resource = image,
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.LightGray.copy(alpha = 0.35f))
            ) {
                Text(
                    text = result.title,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(top = 8.dp, start = 3.dp, end = 3.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = result.byline,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp),
                    color = Color.White,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }


}

