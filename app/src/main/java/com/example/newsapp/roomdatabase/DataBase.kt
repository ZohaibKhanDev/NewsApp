package com.example.newsapp.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [FavItem::class], version = 1)
abstract class DataBase :RoomDatabase(){
   abstract fun favdao():Dao
}