package org.gladcherry.map.model.geo

import org.gladcherry.map.model.search.Geom

data class GeoResponse(
    val address: String,
    val address_compact: String,
    val city: String,
    val country: String,
    val county: String,
    val district: String,
    val geom: Geom,
    val last: String,
    val name: String,
    val neighbourhood: String,
    val plaque: String,
    val poi: String,
    val postal_address: String,
    val postal_code: String,
    val primary: String,
    val province: String,
    val region: String,
    val rural_district: String,
    val village: String
)