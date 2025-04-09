package com.vlad.newsapp.views.main_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vlad.newsapp.R
import com.vlad.newsapp.databinding.FragmentMainBinding
import com.vlad.newsapp.views.base.BaseBindingFragment
import kotlinx.coroutines.launch

class MainFragment: BaseBindingFragment<FragmentMainBinding>() {

    override val layoutId: Int = R.layout.fragment_main
    private var viewModel: MainViewModel? = null
    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsList.adapter = adapter

        var viewModel = ViewModelProvider(this)
            .get(MainViewModel::class.java)


        lifecycleScope.launch(){
            viewModel.data.collect{

                Log.d("ee", "data" + it)
                adapter.submitList(it)
            }
        }



    }

    companion object{
        fun newInstance() = MainFragment()
    }
}