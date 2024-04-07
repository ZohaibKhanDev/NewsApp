package com.example.newsapp.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class FavItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    @ColumnInfo("image")
    val image:String,
    @ColumnInfo("title")
    val tittle:String,
    @ColumnInfo("des")
    val des:String

)
