package com.vlad.newsapp.views.detailed_page

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.vlad.newsapp.R
import com.vlad.newsapp.data.model.ItemPreviewNews
import com.vlad.newsapp.databinding.FragmentDetailedBinding
import com.vlad.newsapp.views.base.BaseBindingFragment

class DetailedFragment: BaseBindingFragment<FragmentDetailedBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_detailed

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val data = arguments?.getParcelable(ARGUMENT_DATA) as? ItemPreviewNews
        binding.apply {
            this.data = data
            data?.url?.let {
                btnSource.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data?.url))
                    startActivity(intent)
                }
            }

        }
        addButtonBack()
    }

    private fun addButtonBack(){
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        // Возврат на предыдущий фрагмент
                        findNavController().navigateUp()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }
    companion object{
        const val ARGUMENT_DATA = "argumentData"
    }
}