package es.travelworld.traveling.data.mapper

import es.travelworld.traveling.data.local.entities.UserEntity
import es.travelworld.traveling.domain.entities.User

fun UserEntity.toUser() : User {
   return User(
       username = this.username,
       password = this.password
   )
}