package org.gladcherry.map.view.map.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gladcherry.map.R
import org.gladcherry.map.base.ViewHolderBinding
import org.gladcherry.map.databinding.ItemSearchBinding
import org.gladcherry.map.model.search.SearchItem
import org.gladcherry.map.view.map.MapViewModel


class SearchAdapter(
    var searchItemList: List<SearchItem> = listOf(),
    private val mapViewModel: MapViewModel
) : RecyclerView.Adapter<SearchAdapter.ItemVH>(), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search, parent, false).apply {
                    findViewById<View>(R.id.rootLayout).setOnClickListener(this@SearchAdapter)
                }
        )

    }

    override fun onBindViewHolder(holderItem: ItemVH, position: Int) {
        holderItem.apply {
            binding.searchItem = searchItemList[position]
            binding.vm = mapViewModel
            binding.position = position
        }
    }

    override fun getItemCount() = searchItemList.size

    class ItemVH(itemLayout: View) : ViewHolderBinding<ItemSearchBinding>(itemLayout)

    override fun onClick(view: View?) {
        val position = (view?.tag) as Int
        mapViewModel.changeSearchVisibility(false)
        mapViewModel.changeCurrentSearch(position)
    }
}
