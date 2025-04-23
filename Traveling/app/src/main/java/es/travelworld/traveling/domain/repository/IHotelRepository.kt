package es.travelworld.traveling.domain.repository

import es.travelworld.traveling.data.remote.hotelmodel.Hotel

interface IHotelRepository {
    suspend fun fetchHotels(): Result<List<Hotel>>;
}