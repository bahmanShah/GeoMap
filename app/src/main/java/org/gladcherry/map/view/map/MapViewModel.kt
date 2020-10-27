package org.gladcherry.map.view.map

import android.util.Log
import androidx.annotation.IdRes
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_map.view.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.gladcherry.map.R
import org.gladcherry.map.model.geo.GeoResponse
import org.gladcherry.map.model.search.SearchResponse
import org.gladcherry.map.util.M_TAG
import java.lang.Exception

class MapViewModel(private val repository: MapRepository) : ViewModel(), LifecycleObserver {
    private val _currentGeo = MutableLiveData<GeoResponse>()
    val currentGeo: LiveData<GeoResponse> = _currentGeo

    private val _currentSearchId = MutableLiveData<Int?>(null)
    val currentSearchId: LiveData<Int?> = _currentSearchId

    private val _searchList = MutableLiveData<SearchResponse>()
    val searchList: LiveData<SearchResponse> = _searchList

    private val _clickedViewId = MutableLiveData<Int>()
    val clickedViewId: LiveData<Int> = _clickedViewId

    private val _searchVisibility = MutableLiveData(false)
    val searchVisibility: LiveData<Boolean> = _searchVisibility

    fun changeSearchVisibility(searchState: Boolean) {
        _searchVisibility.value = searchState
    }

    fun clicked(@IdRes id: Int) {
        when(id){
            R.id.backSearch ->{
                _clickedViewId.value = R.id.backSearch
            }
        }
    }

    fun changeCurrentSearch(currentSearchId: Int) {
        _currentSearchId.value = currentSearchId
    }

    fun reverseGeo(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                withTimeout(3000) {
                    _currentGeo.postValue(repository.reverseGeo(latitude, longitude))
                }
            } catch (exception: Exception) {

            }

        }
    }

    fun doSearch(textSearch: String) {
        viewModelScope.launch {
            try {
                withTimeout(3000) {
                    _searchList.postValue(repository.doSearch(textSearch))
                    Log.d(M_TAG,_searchList.value.toString())
                }
            } catch (exception: Exception) {

            }
        }
    }

}