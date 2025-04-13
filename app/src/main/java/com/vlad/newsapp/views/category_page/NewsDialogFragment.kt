package com.vlad.newsapp.views.category_page

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.vlad.newsapp.R
import com.vlad.newsapp.databinding.FragmentNewsDialogBinding
import java.util.ArrayList

class NewsDialogFragment: DialogFragment() {
    private lateinit var adapter: CategoryAdapter
    private  var data: ArrayList<String>? = null
    private var requestCode: String? = null
    private var selectedItem: String? = null
    private var defaultValue: String = "None"


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var view = FragmentNewsDialogBinding.inflate(layoutInflater)
        var builder = AlertDialog.Builder(context)
            .setView(view.root)

            arguments?.apply {
                data= getStringArrayList(NEWS_DIALOG_DATA)
                requestCode = getString(REQUEST_KEY) ?: ""
                defaultValue = getString(DEFAULT_VALUE) ?: "None"
            }


        adapter = CategoryAdapter({
            selectedItem = it
        }, defaultValue)



        view.listOfItems.adapter = adapter
        view.btnOk.setOnClickListener {

            setFragmentResult(requestCode!!, bundleOf( RESULT_KEY to selectedItem))
            dismiss()
        }

        view.btnCancel.setOnClickListener {
            dismiss()
        }

        adapter.submitList(data)

        return builder.create()
    }

    companion object{
        const val NEWS_DIALOG_DATA = "newsDialogData"
        const val RESULT_KEY = "resultKey"
        const val REQUEST_KEY = "requestKey"
        const val DEFAULT_VALUE = "defaultValue"
    }
}