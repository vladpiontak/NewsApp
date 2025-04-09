package com.vlad.newsapp.views.main_page

import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.newsapp.data.Network
import com.vlad.newsapp.data.NewsRequest
import com.vlad.newsapp.data.entity.ItemPreviewNews
import com.vlad.newsapp.data.entity.Source
import com.vlad.newsapp.data.entity.StatusNewsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val _data = MutableStateFlow<List<ItemPreviewNews>>(listOf())
    val data: StateFlow<List<ItemPreviewNews>> = _data
    init {
        //getDataTest()
        getDataBySearch()
    }

    private fun getDataBySearch(){
        viewModelScope.launch {
            Network().retrofitBuilder.create(NewsRequest::class.java).getEverythingBySearch().enqueue(object:
                Callback<StatusNewsResponse>{
                override fun onResponse(
                    call: Call<StatusNewsResponse>,
                    response: Response<StatusNewsResponse>
                ) {
                    response.body()?.articles?.let{
                        _data.tryEmit(it)
                        Log.d("ee", "data " + it.toString())
                    }
                }

                override fun onFailure(call: Call<StatusNewsResponse>, t: Throwable) {
                    Log.d("ee", "data error", t)
                }
                }
            )

        }
    }
    private fun getDataTest(){
        val list = List(12){
            ItemPreviewNews(
                id =4,
                source = Source(id = "3", "Gizmodo.com"),
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