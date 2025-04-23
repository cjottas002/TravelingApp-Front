package es.travelworld.traveling.domain.services

import es.travelworld.traveling.data.remote.hotelmodel.HotelResponse
import retrofit2.Response
import retrofit2.http.GET

interface IHotelService {
    @GET("listHotels")
    suspend fun getHotels(): Response<HotelResponse>
}