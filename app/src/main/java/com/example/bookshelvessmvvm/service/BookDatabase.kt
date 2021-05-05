package com.example.bookshelvessmvvm.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookshelvessmvvm.model.dataBooks

@Database(entities = arrayOf(dataBooks::class),version = 1)
abstract class BookDatabase:RoomDatabase() {
    abstract fun bookDao():bookDao

    companion object{
        @Volatile private var instance:BookDatabase?=null

        private var lock=Any()

        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?: OpenDatabase(context).also {
                instance=it
            }
        }

        private fun OpenDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            BookDatabase::class.java,"bookDatabase"
        ).build()


    }
}