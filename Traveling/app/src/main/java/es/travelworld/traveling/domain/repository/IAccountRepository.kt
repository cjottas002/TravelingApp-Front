package es.travelworld.traveling.domain.repository

import es.travelworld.traveling.core.response.FrameworkResponse
import es.travelworld.traveling.core.response.login.LoginResponse
import es.travelworld.traveling.core.response.register.RegisterResponse

interface IAccountRepository {
    suspend fun register(username: String, pass: String): FrameworkResponse<RegisterResponse>
    suspend fun login(username: String, pass: String): FrameworkResponse<LoginResponse>

    suspend fun localLogin(username: String, pass: String) : FrameworkResponse<LoginResponse>
    suspend fun localRegister(username: String, pass: String) : FrameworkResponse<RegisterResponse>
}