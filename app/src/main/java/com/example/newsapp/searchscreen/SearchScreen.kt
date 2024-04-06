package com.example.newsapp.searchscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
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
import androidx.navigation.NavController
import com.example.newsapp.newsapi.MainViewModel
import com.example.newsapp.newsapi.Repository
import com.example.newsapp.newsapi.ResultState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    val repository= remember {
        Repository()
    }
    val viewModel = remember {
        MainViewModel(repository)
    }

    var searchBarState by remember {
        mutableStateOf("")
    }
    var isSearch by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = isSearch) {
        viewModel.getAllSearch(searchBarState)
    }
    val state by viewModel.allSearch.collectAsState()
    when(state){
        is ResultState.Error->{
            val error=(state as ResultState.Error).error
            Text(text = error.toString())
            isSearch=false
        }
        is ResultState.Loading->{
            isSearch=true
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        is ResultState.Success->{
            isSearch=false
            val succes=(state as ResultState.Success).response

        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        SearchBar(query =searchBarState , onQueryChange = {
                                                          searchBarState=it
        }, onSearch ={

        } , active =true , onActiveChange = {

        }) {

        }
    }


}