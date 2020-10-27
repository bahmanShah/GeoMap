package org.gladcherry.map.view.map

import org.gladcherry.map.base.Repository
import org.gladcherry.map.data.network.ApiService
import org.gladcherry.map.model.geo.GeoResponse
import org.gladcherry.map.model.search.SearchResponse

class MapRepository(private val service: ApiService):Repository {
    suspend fun reverseGeo(latitude: Double, longitude: Double): GeoResponse? {
        return service.reverseGeo(latitude,longitude)
    }
    suspend fun doSearch(textSearch: String): SearchResponse? {
        return service.doSearch(textSearch)
    }
}