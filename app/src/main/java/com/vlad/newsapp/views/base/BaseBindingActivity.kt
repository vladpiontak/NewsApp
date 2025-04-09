package com.vlad.newsapp.views.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.findNavController

abstract class BaseBindingActivity<Binding: ViewDataBinding>: AppCompatActivity() {
    private lateinit var _binding: Binding
    protected val binding: Binding get() = _binding
    @get:LayoutRes
    protected abstract val layoutId: Int

    protected lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        setContentView(_binding.root)
    }


}