package com.vlad.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.vlad.newsapp.databinding.ActivityMainBinding
import com.vlad.newsapp.views.base.BaseBindingActivity
import com.vlad.newsapp.views.category_page.CategoryFragment
import com.vlad.newsapp.views.main_page.MainFragment

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main

    private lateinit var navController: NavController

    override fun onStart() {
        super.onStart()

        navController = findNavController(binding.fragmentHost.id)
        navController.navigate(R.id.mainFragment)

    }
}