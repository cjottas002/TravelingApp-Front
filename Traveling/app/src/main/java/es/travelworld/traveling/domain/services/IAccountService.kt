package es.travelworld.traveling.domain.services

import es.travelworld.traveling.core.request.login.LoginRequest
import es.travelworld.traveling.core.request.register.RegisterRequest
import es.travelworld.traveling.core.response.FrameworkResponse
import es.travelworld.traveling.core.response.login.LoginResponse
import es.travelworld.traveling.core.response.register.RegisterResponse
import es.travelworld.traveling.data.remote.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IAccountService {
    @POST("api/account/login")
    suspend fun login(@Body request: LoginRequest): Response<FrameworkResponse<LoginResponse>>

    @POST("api/account/register")
    suspend fun register(@Body request: RegisterRequest): Response<FrameworkResponse<RegisterResponse>>
}
