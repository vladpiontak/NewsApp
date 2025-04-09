package com.vlad.newsapp.views.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.vlad.newsapp.R
import com.vlad.newsapp.databinding.FragmentMainBinding
import com.vlad.newsapp.views.base.BaseBindingFragment

class MainFragment: BaseBindingFragment<FragmentMainBinding>() {

    override val layoutId: Int = R.layout.fragment_main



    companion object{
        fun newInstance() = MainFragment()
    }
}