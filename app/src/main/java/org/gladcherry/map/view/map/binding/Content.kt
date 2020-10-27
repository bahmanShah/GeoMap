package org.gladcherry.map.view.map.binding

import android.util.Log
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.gladcherry.map.R
import org.gladcherry.map.model.search.SearchItem
import org.gladcherry.map.util.M_TAG
import org.gladcherry.map.view.map.MapActivity
import org.gladcherry.map.view.map.MapViewModel
import org.gladcherry.map.view.map.adapter.SearchAdapter

@BindingAdapter("searchList", "viewModel", requireAll = true)
fun recyclerAdapter(
    recyclerView: RecyclerView,
    searchList: List<SearchItem>?,
    viewModel: MapViewModel
) {
    Log.d(M_TAG,"recyclerAdapter :$searchList")
    searchList?.let {
        if (searchList.isNotEmpty()) {
            recyclerView.adapter?.run {
                if (this is SearchAdapter) {
                    this.searchItemList = searchList
                    this.notifyDataSetChanged()
                }
            } ?: run {
                recyclerView.apply {
                    adapter = SearchAdapter(searchList, viewModel)
                    scheduleLayoutAnimation()
                    layoutAnimation = AnimationUtils.loadLayoutAnimation(
                        recyclerView.context,
                        R.anim.layout_animation_fall_down
                    )
                }
            }
        }
    }
}