package es.travelworld.traveling.domain.services

import es.travelworld.traveling.core.request.user.UserRequest
import es.travelworld.traveling.core.response.FrameworkResponse
import es.travelworld.traveling.core.response.user.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface IUserService {

    @GET("api/user/getAllUsers")
    suspend fun getAllUsers(
        @Header("Authorization") token: String,
        @QueryMap query: Map<String, String>
    ): Response<FrameworkResponse<UserResponse>>
}