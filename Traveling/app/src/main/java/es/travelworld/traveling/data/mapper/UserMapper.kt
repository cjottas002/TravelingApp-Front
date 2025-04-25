package es.travelworld.traveling.data.mapper

import es.travelworld.traveling.core.response.user.UserResponse
import es.travelworld.traveling.data.local.entities.UserEntity
import es.travelworld.traveling.domain.entities.User

fun UserEntity.toUser() : User {
   return User(
       username = this.username,
       password = this.password
   )
}

fun UserResponse.toEntity(): UserEntity =
    UserEntity(
        id    =     this.id!!,
        username  = this.name!!,
        email =     this.email!!,
        password =  this.password!!,
        updateAt =  this.updatedAt,
    )