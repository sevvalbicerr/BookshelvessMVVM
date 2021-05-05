package com.example.bookshelvessmvvm.view

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookshelvessmvvm.R
import com.example.bookshelvessmvvm.adapter.bookAdapter
import com.example.bookshelvessmvvm.databinding.FragmentBookListBinding
import com.example.bookshelvessmvvm.viewmodel.bookDetailViewModel
import com.example.bookshelvessmvvm.viewmodel.bookListViewModel


class bookList : Fragment() {
    private lateinit var  viewModel:bookListViewModel
    private val adapter=bookAdapter(arrayListOf())
    private var binding:FragmentBookListBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(bookListViewModel::class.java)
        binding= FragmentBookListBinding.bind(view)
        binding!!.recyclerviewBookList.layoutManager=LinearLayoutManager(context)
        binding!!.recyclerviewBookList.adapter=adapter

        viewModel.refreshData()
        observe()


    }
    fun observe(){
        viewModel.books.observe(viewLifecycleOwner, Observer {
            binding!!.recyclerviewBookList.visibility=View.VISIBLE
            adapter.updateList(it)
        })
    }
}