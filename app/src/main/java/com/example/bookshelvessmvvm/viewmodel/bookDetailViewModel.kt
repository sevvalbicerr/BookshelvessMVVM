package com.example.bookshelvessmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.bookshelvessmvvm.model.dataBooks
import com.example.bookshelvessmvvm.service.BookDatabase
import com.example.bookshelvessmvvm.service.dB
import kotlinx.coroutines.launch

class bookDetailViewModel(application:Application):BaseViewModel(application) {
    val bookLiveData=MutableLiveData<dataBooks>()

    fun getDataFromRoom(uuid:Int){
        launch {
            val dao=BookDatabase(getApplication()).bookDao()
            val book=dao.getBook(uuid)
            bookLiveData.value=book
        }
    }
}