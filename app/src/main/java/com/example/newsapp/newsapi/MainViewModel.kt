package com.example.newsapp.newsapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.art.Art
import com.example.newsapp.healthy.Healthy
import com.example.newsapp.politices.Politices
import com.example.newsapp.roomdatabase.FavItem
import com.example.newsapp.searchscreen.Search
import com.example.newsapp.sports.Sports
import com.example.newsapp.sundayreview.SundayReview
import com.example.newsapp.technlogy.Technlogy
import com.example.newsapp.worldnews.World
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {
    private val _allNews= MutableStateFlow<ResultState<News>>(ResultState.Loading)
    val allNews:StateFlow<ResultState<News>> =_allNews.asStateFlow()

    private val _allHealthy= MutableStateFlow<ResultState<Healthy>>(ResultState.Loading)
    val allHealthy:StateFlow<ResultState<Healthy>> =_allHealthy.asStateFlow()

    private val _allTechnlogy= MutableStateFlow<ResultState<Healthy>>(ResultState.Loading)
    val allTechnlogy:StateFlow<ResultState<Healthy>> =_allTechnlogy.asStateFlow()

    private val _allPolitics= MutableStateFlow<ResultState<Healthy>>(ResultState.Loading)
    val allPolitics:StateFlow<ResultState<Healthy>> =_allPolitics.asStateFlow()

    private val _allArt= MutableStateFlow<ResultState<Healthy>>(ResultState.Loading)
    val allArt:StateFlow<ResultState<Healthy>> =_allArt.asStateFlow()

    private val _allSports= MutableStateFlow<ResultState<Healthy>>(ResultState.Loading)
    val allSports:StateFlow<ResultState<Healthy>> =_allSports.asStateFlow()

    private val _allSundayReview= MutableStateFlow<ResultState<Healthy>>(ResultState.Loading)
    val allSundayReview: StateFlow<ResultState<Healthy>> =_allSundayReview.asStateFlow()


    private val _allWorldNews= MutableStateFlow<ResultState<World>>(ResultState.Loading)
    val allWorldNews:StateFlow<ResultState<World>> =_allWorldNews.asStateFlow()

    private val _allSearch=MutableStateFlow<ResultState<Search>>(ResultState.Loading)
    val allSearch:StateFlow<ResultState<Search>> =_allSearch.asStateFlow()

    private val _allFav=MutableStateFlow<ResultState<List<FavItem>>>(ResultState.Loading)
    val allFav:StateFlow<ResultState<List<FavItem>>> =_allFav.asStateFlow()

    private val _allInsert=MutableStateFlow<ResultState<Unit>>(ResultState.Loading)
    val allInsert:StateFlow<ResultState<Unit>> =_allInsert.asStateFlow()


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

    fun getAllFav(){
        viewModelScope.launch {
            _allFav.value=ResultState.Loading
            try {
                val response=repository.getAllFav()
                _allFav.value=ResultState.Success(response)
            }catch (e:Exception){
                _allFav.value=ResultState.Error(e)
            }
        }
    }


    fun getAllInsert(favItem: FavItem){
        viewModelScope.launch {
            _allInsert.value=ResultState.Loading
            try {
                val response=repository.Insert(favItem)
                _allInsert.value=ResultState.Success(response)
            }catch (e:Exception){
                _allInsert.value=ResultState.Error(e)
            }
        }
    }
}