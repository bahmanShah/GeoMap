package org.gladcherry.map.model.search

import com.google.gson.annotations.SerializedName
import org.gladcherry.map.model.search.SearchItem

data class SearchResponse(
    @SerializedName("odata.count")
    val dataCount: Int,
    @SerializedName("value")
    val searchItems: List<SearchItem>
)