package es.travelworld.traveling.data.remote.hotelmodel

import com.google.gson.annotations.SerializedName

data class Coordinate(@SerializedName("lat") val lat: Double, @SerializedName("lon") val lon: Double)