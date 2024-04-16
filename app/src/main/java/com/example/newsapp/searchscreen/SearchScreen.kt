package com.example.newsapp.searchscreen

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.newsapp.navigation.Screen
import com.example.newsapp.newsapi.MainViewModels
import com.example.newsapp.newsapi.Repository
import com.example.newsapp.newsapi.ResultState
import com.example.newsapp.roomdatabase.DataBase
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    val viewModel:MainViewModels= koinInject()


    var searchBarState by remember {
        mutableStateOf("")
    }
    var isSearch by remember {
        mutableStateOf(false)
    }
    var searchData by remember {
        mutableStateOf<Search?>(null)
    }


    val state by viewModel.allSearch.collectAsState()
    when (state) {
        is ResultState.Error -> {
            val error = (state as ResultState.Error).error
            Text(text = error.toString())
            isSearch = false
        }

        is ResultState.Loading -> {
            isSearch = true
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ResultState.Success -> {
            isSearch = false
            val succes = (state as ResultState.Success).response
            searchData = succes
        }
    }




    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        SearchBar(query = searchBarState, onQueryChange = {
            searchBarState = it
        }, onSearch = {
            viewModel.getAllSearch(it)
        }, active = true, onActiveChange = {

        },
            placeholder = {
                Text(text = "Search", color = Color.White)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            trailingIcon = {
                if (searchBarState >= 1.toString()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            searchBarState = ""
                        })
                }

            },
            colors = SearchBarDefaults.colors(
                dividerColor = Color.White,
                containerColor = Color(0XFF0f1029),
                inputFieldColors = TextFieldDefaults.colors(
                    Color.White
                )
            )

        ) {
            LazyColumn(

            ) {
                searchData?.response?.docs?.let { news ->
                    items(news) {
                        SearchItem(doc = it, navController = navController)
                    }

                }
            }
        }
    }
}


@Composable
fun SearchItem(doc: Doc, navController: NavController) {
    Card(

        modifier = Modifier
            .fillMaxWidth()

            .clickable {
                navController.navigate(
                    Screen.DetailScreen.route +
                            "/${Uri.encode("https://nytimes.com/${doc.multimedia[0].url}")}/${doc.abstract}/${doc.leadParagraph}/${doc.typeOfMaterial}/${doc.pubDate}/${doc.abstract}/${doc.newsDesk}"
                )
            }

            .height(400.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(Color.White),

        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            AsyncImage(
                model = "https://nytimes.com/${doc.multimedia[0].url}",
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )


        }


    }
}