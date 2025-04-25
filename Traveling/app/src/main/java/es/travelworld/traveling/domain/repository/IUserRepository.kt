package es.travelworld.traveling.domain.repository

import es.travelworld.traveling.core.request.user.UserRequest
import es.travelworld.traveling.core.response.FrameworkResponse
import es.travelworld.traveling.core.response.user.UserResponse

interface IUserRepository {
    suspend fun getAllUsers(token: String, userRequest: UserRequest) : FrameworkResponse<UserResponse>
}