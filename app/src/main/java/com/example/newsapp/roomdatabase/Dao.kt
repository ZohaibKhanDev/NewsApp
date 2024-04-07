package com.example.newsapp.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface Dao {
    @Query("SELECT * FROM FavItem")
    fun getAllFav():List<FavItem>

    @Insert
    fun Insert(favItem: FavItem)
}