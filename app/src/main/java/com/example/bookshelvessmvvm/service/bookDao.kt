package com.example.bookshelvessmvvm.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bookshelvessmvvm.model.dataBooks

@Dao
interface bookDao {
    @Insert
     suspend fun insertAll(vararg book: dataBooks):List<Long>

    @Query("SELECT * FROM dataBooks")
    suspend fun getAllBook():List<dataBooks>

    @Query("SELECT * FROM databooks WHERE uuid= :bookId")
    suspend fun getBook(bookId:Int ):dataBooks

    @Query("DELETE FROM dataBooks")
    suspend fun deleteAllBook()
}