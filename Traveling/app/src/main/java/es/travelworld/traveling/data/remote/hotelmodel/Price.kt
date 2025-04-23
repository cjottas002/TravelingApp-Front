package es.travelworld.traveling.data.remote.hotelmodel


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("current") val current: String,
    @SerializedName("exactCurrent") val exactCurrent: Double,
    @SerializedName("old") val old: String?
)
