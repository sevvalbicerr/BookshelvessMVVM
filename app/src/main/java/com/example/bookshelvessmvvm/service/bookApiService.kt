package com.example.bookshelvessmvvm.service

import com.example.bookshelvessmvvm.model.dataBooks
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class bookApiService {
//https://gist.github.com/nanotaboada/6396437
    private val baseUrl= "https://raw.githubusercontent.com/"
    private val api =Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(bookAPI::class.java)


    fun getBook():Single<List<dataBooks>>{
        return api.getBook()
    }
}