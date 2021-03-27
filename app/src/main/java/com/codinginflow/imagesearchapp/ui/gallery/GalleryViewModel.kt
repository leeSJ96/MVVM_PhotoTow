package com.codinginflow.imagesearchapp.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.codinginflow.imagesearchapp.data.UnsplashRepository
import retrofit2.http.Query
import javax.inject.Inject

class GalleryViewModel @ViewModelInject constructor(private val repository: UnsplashRepository): ViewModel() {

    private val currentQuery = MutableLiveData(DEFFAULT_QUERY)

    val photos = currentQuery.switchMap { currentQuery ->
        repository.getSearchResults(currentQuery).cachedIn(viewModelScope)
    }

    fun serchphotos(query: String){
        currentQuery.value = query
    }

    companion object{
        private const val DEFFAULT_QUERY = "cats"
    }
}