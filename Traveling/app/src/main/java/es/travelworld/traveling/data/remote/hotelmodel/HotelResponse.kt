package es.travelworld.traveling.data.remote.hotelmodel

import com.google.gson.annotations.SerializedName
import es.travelworld.traveling.data.remote.Pagination

data class HotelResponse(
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("results") val results: List<Hotel>,
    @SerializedName("pagination") val pagination: Pagination?
)