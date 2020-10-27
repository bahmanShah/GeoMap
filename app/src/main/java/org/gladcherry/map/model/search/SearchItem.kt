package org.gladcherry.map.model.search

import com.google.gson.annotations.SerializedName

data class SearchItem(
    val address: String,
    val city: String,
    val county: String,
    val district: String,
    @SerializedName("fclass")
    val fClass: String,
    val geom: Geom,
    val neighborhood: String,
    val province: String,
    val region: String,
    val title: String,
    val type: String
)