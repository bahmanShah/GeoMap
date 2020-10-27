package org.gladcherry.map.data.network

import org.gladcherry.map.model.geo.GeoResponse
import org.gladcherry.map.model.search.SearchResponse
import org.gladcherry.map.util.HEAD_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(HEAD_KEY)
    @GET("reverse")
    suspend fun reverseGeo(@Query("lat") lat:Double, @Query("lon") lon:Double): GeoResponse

    @Headers(HEAD_KEY)
    @GET("/search/v2/autocomplete")
    suspend fun doSearch(@Query("text") text:String): SearchResponse
}
