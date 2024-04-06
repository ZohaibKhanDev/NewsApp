package com.example.newsapp.newsapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {
    private val _allNews= MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allNews:StateFlow<ResultState<News>> =_allNews.asStateFlow()

    private val _allHealthy= MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allHealthy:StateFlow<ResultState<News>> =_allNews.asStateFlow()

    private val _allTechnlogy= MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allTechnlogy:StateFlow<ResultState<News>> =_allNews.asStateFlow()

    private val _allPolitics= MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allPolitics:StateFlow<ResultState<News>> =_allNews.asStateFlow()

    private val _allArt= MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allArt:StateFlow<ResultState<News>> =_allNews.asStateFlow()

    private val _allSports= MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allSports:StateFlow<ResultState<News>> =_allNews.asStateFlow()

    private val _allSundayReview= MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allSundayReview:StateFlow<ResultState<News>> =_allNews.asStateFlow()


    private val _allWorldNews= MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allWorldNews:StateFlow<ResultState<News>> =_allWorldNews.asStateFlow()

    private val _allSearch=MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allSearch:StateFlow<ResultState<News>> =_allSearch.asStateFlow()


    fun getAllSearch(query:String){
        viewModelScope.launch {
            _allSearch.value=ResultState.Loading
            try {
                val response=repository.Search(query)
                _allSearch.value=ResultState.Success(response)
            }catch (e:Exception){
                _allSearch.value=ResultState.Error(e)
            }
        }
    }
      fun getAllNews(){
        viewModelScope.launch {
            _allNews.value=ResultState.Loading
            try {
                val response=repository.getAllNews()
                _allNews.value=ResultState.Success(response)
            }catch (e:Exception){
                _allNews.value=ResultState.Error(e)
            }
        }
    }

    fun getAllHealthy(){
        viewModelScope.launch {
            _allHealthy.value=ResultState.Loading
            try {
                val response=repository.Healthy()
                _allHealthy.value=ResultState.Success(response)
            }catch (e:Exception){
                _allHealthy.value=ResultState.Error(e)
            }
        }
    }


    fun getAllTechnlogy(){
        viewModelScope.launch {
            _allTechnlogy.value=ResultState.Loading
            try {
                val response=repository.Technlogy()
                _allTechnlogy.value=ResultState.Success(response)
            }catch (e:Exception){
                _allTechnlogy.value=ResultState.Error(e)
            }
        }
    }

    fun getAllPolitics(){
        viewModelScope.launch {
            _allPolitics.value=ResultState.Loading
            try {
                val response=repository.Politics()
                _allPolitics.value=ResultState.Success(response)
            }catch (e:Exception){
                _allPolitics.value=ResultState.Error(e)
            }
        }
    }

    fun getAllArt(){
        viewModelScope.launch {
            _allArt.value=ResultState.Loading
            try {
                val response=repository.Art()
                _allArt.value=ResultState.Success(response)
            }catch (e:Exception){
                _allArt.value=ResultState.Error(e)
            }
        }
    }

    fun getAllSports(){
        viewModelScope.launch {
            _allSports.value=ResultState.Loading
            try {
                val response=repository.Sports()
                _allSports.value=ResultState.Success(response)
            }catch (e:Exception){
                _allSports.value=ResultState.Error(e)
            }
        }
    }

    fun getAllSundayReview(){
        viewModelScope.launch {
            _allSundayReview.value=ResultState.Loading
            try {
                val response=repository.SundayReview()
                _allSundayReview.value=ResultState.Success(response)
            }catch (e:Exception){
                _allSundayReview.value=ResultState.Error(e)
            }
        }
    }

    fun getAllWorldNews(){
        viewModelScope.launch {
            _allWorldNews.value=ResultState.Loading
            try {
                val response=repository.WorldNews()
                _allWorldNews.value=ResultState.Success(response)
            }catch (e:Exception){
                _allWorldNews.value=ResultState.Error(e)
            }
        }
    }
}