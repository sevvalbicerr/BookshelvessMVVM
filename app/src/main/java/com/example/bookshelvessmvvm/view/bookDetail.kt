package com.example.bookshelvessmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bookshelvessmvvm.R
import com.example.bookshelvessmvvm.databinding.FragmentBookDetailBinding
import com.example.bookshelvessmvvm.util.downloadImage
import com.example.bookshelvessmvvm.util.makePlaceHolder
import com.example.bookshelvessmvvm.viewmodel.bookDetailViewModel

class bookDetail : Fragment() {
    private lateinit var vm:bookDetailViewModel
    private var binding:FragmentBookDetailBinding?=null
    private var bookID=0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
             bookID= bookDetailArgs.fromBundle(it).id
        }
        binding=FragmentBookDetailBinding.bind(view)
        vm=ViewModelProviders.of(this).get(bookDetailViewModel::class.java)

        observeData()
        vm.getDataFromRoom(bookID)
    }
    fun observeData(){
        vm.bookLiveData.observe(viewLifecycleOwner, Observer { bookData->
            bookData?.let { data->
                binding?.let {
                    it.idTitle.text=data.title
                    it.idAuthor.text=data.author
                    it.idYearWritten.text=data.year_written
                    it.idEdition.text=data.edition
                    it.idPrice.text=data.price.toString()
                    context?.let {contextit->
                        binding!!.idimgbook.downloadImage(data.url, makePlaceHolder(contextit))
                    }


                }
            }
        })
    }
}