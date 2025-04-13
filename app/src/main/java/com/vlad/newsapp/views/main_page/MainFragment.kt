package com.vlad.newsapp.views.main_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.vlad.newsapp.R
import com.vlad.newsapp.databinding.FragmentMainBinding
import com.vlad.newsapp.views.base.BaseBindingFragment
import com.vlad.newsapp.views.list_news.ListOfArticles
import com.vlad.newsapp.views.list_news.ListOfNewsFragment
import kotlinx.coroutines.launch

class MainFragment: BaseBindingFragment<FragmentMainBinding>() {

    override val layoutId: Int = R.layout.fragment_main
    private val viewModel: MainViewModel by viewModels()
    private lateinit var listFragment:ListOfNewsFragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listFragment = childFragmentManager.findFragmentById(R.id.list_fragment) as ListOfNewsFragment
       // binding.newsList.adapter = adapter



        lifecycleScope.launch(){
            viewModel.data.collect{

                Log.d("ee", "data" + it)
                listFragment.updateListOfArticles(it)
            }
        }

        setToolbarWithSearch()


    }

    private fun setToolbarWithSearch(){
        requireActivity().addMenuProvider(object:MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_screen_toolbar, menu)

                val searchItem = menu.findItem(R.id.item_search).actionView as SearchView
                searchItem.queryHint = getString(R.string.search_news)

                searchItem.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            query?.let { viewModel.updateQueryText(it, true) }

                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {

                        newText?.let { viewModel.updateQueryText(it, false)  }
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem){

                    else -> return false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}