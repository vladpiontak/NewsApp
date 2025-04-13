package com.vlad.newsapp.views.main_page

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
import com.vlad.newsapp.views.list_news.ListOfNewsFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: BaseBindingFragment<FragmentMainBinding>() {
    override val layoutId: Int = R.layout.fragment_main
    private val viewModel: MainViewModel by viewModel()
    private lateinit var listFragment:ListOfNewsFragment
    private lateinit var searchMenuItem: MenuItem


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listFragment = childFragmentManager.findFragmentById(R.id.list_fragment) as ListOfNewsFragment
        listFragment.apply {
            updatePage = {
                viewModel.getDataBySearch(viewModel.queryText.value)
            }
        }

        lifecycleScope.launch(){
            viewModel.data.collect{
                listFragment.updateListOfArticles(it)
                }
            }

        setToolbarWithSearch()
    }

    private fun setToolbarWithSearch(){
        requireActivity().addMenuProvider(object:MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_screen_toolbar, menu)
                searchMenuItem = menu.findItem(R.id.item_search)
                val searchItem = menu.findItem(R.id.item_search).actionView as SearchView
                searchItem.queryHint = getString(R.string.search_news)

                searchItem.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            viewModel.updateQueryText(it, true)
                        }

                        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view?.windowToken, 0)

                        (searchMenuItem.actionView as SearchView).clearFocus()
                        searchMenuItem.collapseActionView()
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
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