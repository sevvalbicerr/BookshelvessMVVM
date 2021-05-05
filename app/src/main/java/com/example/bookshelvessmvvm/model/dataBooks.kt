package com.example.bookshelvessmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class dataBooks(
    @ColumnInfo(name="title")
    @SerializedName("title")
    val title:String,
    @ColumnInfo(name="author")
    @SerializedName("author")
    val author:String,
    @ColumnInfo(name="year_written")
    @SerializedName("year_written")
    val year_written:String,
    @ColumnInfo(name="edition")
    @SerializedName("edition")
    val edition:String,
    @ColumnInfo(name="price")
    @SerializedName("price")
    val price:Float,
    @ColumnInfo(name="url")
    @SerializedName("url")
    var url:String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}