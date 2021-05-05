package com.example.bookshelvessmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelvessmvvm.databinding.CardviewBinding
import com.example.bookshelvessmvvm.model.dataBooks
import com.example.bookshelvessmvvm.util.downloadImage
import com.example.bookshelvessmvvm.util.makePlaceHolder
import com.example.bookshelvessmvvm.view.bookListDirections

class bookAdapter(val listOfBook:ArrayList<dataBooks>):RecyclerView.Adapter<bookAdapter.BookViewHolder>() {
    class BookViewHolder(val itemBinding:CardviewBinding) :RecyclerView.ViewHolder(itemBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val mView=CardviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BookViewHolder(mView)

    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        with(holder){
            itemBinding.cardKitapIsmi.text=listOfBook[position].title
            itemBinding.cardKitapKonu.text=listOfBook[position].author
            itemBinding.cardViewGorsel.downloadImage(listOfBook[position].url, makePlaceHolder(itemView.context))
            itemView.setOnClickListener {
                val action=bookListDirections.actionBookListtoBookDetail(listOfBook[position].uuid)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfBook.size
    }

    fun updateList(newList:List<dataBooks>){
        listOfBook.clear()
        listOfBook.addAll(newList)
        notifyDataSetChanged()
    }
}