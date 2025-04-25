package es.travelworld.traveling.data.repository

import es.travelworld.traveling.core.extensions.toQueryMap
import es.travelworld.traveling.core.request.user.UserRequest
import es.travelworld.traveling.core.response.FrameworkResponse
import es.travelworld.traveling.core.response.user.UserResponse
import es.travelworld.traveling.data.local.daos.UserDao
import es.travelworld.traveling.data.local.entities.UserEntity
import es.travelworld.traveling.domain.repository.IUserRepository
import es.travelworld.traveling.domain.services.IUserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService : IUserService, private val userDao : UserDao) : IUserRepository  {

    override suspend fun getAllUsers(token: String, userRequest: UserRequest): FrameworkResponse<UserResponse> {
        return userService.getAllUsers(token, userRequest.toQueryMap()).body()!!
    }

    suspend fun insertUser(users: List<UserEntity>) {
        userDao.insertAll(users)
    }
}