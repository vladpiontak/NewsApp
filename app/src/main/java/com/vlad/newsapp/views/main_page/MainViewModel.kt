package com.vlad.newsapp.views.main_page

import androidx.lifecycle.ViewModel
import com.vlad.newsapp.data.ItemPreviewNews
import com.vlad.newsapp.data.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import java.util.UUID

class MainViewModel: ViewModel() {
    private val _data = MutableStateFlow<List<ItemPreviewNews>>(listOf())
    val data: StateFlow<List<ItemPreviewNews>> = _data
    init {
        getData()
    }
    private fun getData(){
        val list = List(12){
            ItemPreviewNews(
                id =4,
                source = Source(id = 3, "Gizmodo.com"),
                author = "Jesse Coburn, ProPublica",
                title = "U.S. Housing Agency Considers Launching Crypto Experiment",
                description = "HUD is pondering using the blockchain and a stablecoin. One HUD official derided it as “monopoly money.",
                url = "https://gizmodo.com/u-s-housing-agency-considers-launching-crypto-experiment-2000573596",
                urlImage = "https://gizmodo.com/app/uploads/2025/03/GettyImages-2203279232.jpg",
                publishedAt = "2025-03-09T17:50:56Z",
                content = "ProPublica is a Pulitzer Prize-winning investigative newsroom. Sign up for The Big Story newsletter to receive stories like this one in your inbox.The U.S. Department of Housing and Urban Development… [+9853 chars]"

            )
        }
        _data.tryEmit(list)
    }
}