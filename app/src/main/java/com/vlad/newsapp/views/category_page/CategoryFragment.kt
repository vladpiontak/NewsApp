package com.vlad.newsapp.views.category_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlad.newsapp.R
import com.vlad.newsapp.databinding.FragmentCategoryBinding
import com.vlad.newsapp.views.base.BaseBindingFragment

class CategoryFragment : BaseBindingFragment<FragmentCategoryBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_category


    companion object {
        fun newInstance() =
            CategoryFragment()
    }
}