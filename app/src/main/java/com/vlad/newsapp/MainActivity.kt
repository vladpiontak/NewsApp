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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomNavBar.setOnItemSelectedListener {
            val idFragment = when(it.itemId){
                R.id.item_main -> R.id.mainFragment
                R.id.item_category -> R.id.categoryFragment
                else -> R.id.mainFragment
            }
            navController.navigate(idFragment)
            return@setOnItemSelectedListener true
        }
    }

    override fun onStart() {
        super.onStart()

        navController = findNavController(binding.fragmentHost.id)
        navController.navigate(R.id.mainFragment)

    }
}