package es.travelworld.traveling.core.response.login

import es.travelworld.traveling.core.response.ResponseDto

data class LoginResponse(val userId: String, val token: String) : ResponseDto()