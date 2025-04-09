package com.vlad.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.vlad.newsapp.databinding.ActivityMainBinding
import com.vlad.newsapp.views.category_page.CategoryFragment
import com.vlad.newsapp.views.main_page.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var view = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(view.root)


        val fragment= if (savedInstanceState == null){
            CategoryFragment.newInstance()
        }else{
            supportFragmentManager.findFragmentById(view.fragmentHost.id)!!
        }

        supportFragmentManager.beginTransaction()
            .replace(view.fragmentHost.id, fragment)
            .commit()
    }
}