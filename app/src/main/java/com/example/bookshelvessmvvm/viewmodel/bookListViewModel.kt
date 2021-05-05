package com.example.bookshelvessmvvm.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.bookshelvessmvvm.model.dataBooks
import com.example.bookshelvessmvvm.service.BookDatabase
import com.example.bookshelvessmvvm.service.bookApiService
import com.example.bookshelvessmvvm.util.SharedP
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.lang.Exception

class bookListViewModel(application:Application):BaseViewModel(application) {
    val books=MutableLiveData<List<dataBooks>>()
    private var updateTime = 10 * 60 * 1000 * 1000 * 1000L

    private val apiService=bookApiService()
    private val disposable = CompositeDisposable()
    private val PrivateSharedP=SharedP(getApplication())

    fun refreshData(){

        val recordTime = PrivateSharedP.zamaniAl()
        if (recordTime != null && recordTime != 0L && System.nanoTime() - recordTime < updateTime){
            //Sqlite'tan çek
            getDataFromSqlite()
        } else {
            getDAtaFromInternet()
        }

    }
    private fun getDataFromSqlite(){
        launch{
            val list = BookDatabase(getApplication()).bookDao().getAllBook()
            showBook(list)
        }


    }
    private fun showBook(bookList: List<dataBooks>){
        books.value=bookList

    }
    private fun getDAtaFromInternet(){
        disposable.add(
            apiService.getBook()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<dataBooks>>(){
                    override fun onSuccess(t: List<dataBooks>) {
                        RecordToSql(t)
                        //books.value=t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()

                    }

                })
        )
    }
    private fun RecordToSql(bookList:List<dataBooks>){
        println(bookList.size)
        bookList?.forEach {
            println(it)
        }
        launch{
            try{
                val dao = BookDatabase(getApplication()).bookDao()
                dao.deleteAllBook()
                val uuidListesi = dao.insertAll(*bookList.toTypedArray())
                var i = 0
                while (i < bookList.size) {
                   bookList[i].uuid = uuidListesi[i].toInt()
                    //println(uuidListesi[i])
                    i++
                }
                showBook(bookList)
            }catch (e:Exception){
                e.printStackTrace()
                println("burda patladı")
            }


            PrivateSharedP.zamaniKaydet(System.nanoTime())
        }

    }
}