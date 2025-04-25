package es.travelworld.traveling.core.response.user

import es.travelworld.traveling.core.response.ResponseDto

data class UserResponse(
    val id: String?,
    val name: String?,
    val email: String?,
    val password: String?,
    val updatedAt: Long
) : ResponseDto()
