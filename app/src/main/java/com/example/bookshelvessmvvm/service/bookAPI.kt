package com.example.bookshelvessmvvm.service

import com.example.bookshelvessmvvm.model.dataBooks
import io.reactivex.Single
import retrofit2.http.GET

interface bookAPI {
//https://github.com/sevvalbicerr/BookshelvessWithMVVM/blob/main/BookDataset.json
// Baseurl -> https://github.com/
    //sevvalbicerr/BookshelvessWithMVVM/blob/main/BookDataset.json
    //https://www.googleapis.com/books/v1/volumes?q=search+terms


//https://raw.githubusercontent.com/sevvalbicerr/BookshelvessWithMVVM/main/BookDataset.json

    // get : Retrofit kütüphanesiyle geliyor
    @GET("sevvalbicerr/BookshelvessWithMVVM/main/BookDataset.json")
    fun getBook():Single<List<dataBooks>>
}