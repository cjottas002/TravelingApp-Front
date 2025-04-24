package es.travelworld.traveling.data.repository

import es.travelworld.traveling.core.response.FrameworkResponse
import es.travelworld.traveling.core.request.login.LoginRequest
import es.travelworld.traveling.core.request.register.RegisterRequest
import es.travelworld.traveling.core.response.login.LoginResponse
import es.travelworld.traveling.core.response.register.RegisterResponse
import es.travelworld.traveling.data.local.daos.UserDao
import es.travelworld.traveling.domain.services.IAccountService
import es.travelworld.traveling.data.remote.User
import es.travelworld.traveling.domain.repository.IAccountRepository
import javax.inject.Inject

class AccountRepository @Inject constructor(private val accountService: IAccountService, private val userDao: UserDao) : IAccountRepository {

    override suspend fun register(username: String, pass: String): FrameworkResponse<RegisterResponse> {
        return accountService.register(RegisterRequest(username, pass)).body()!!
    }

    override suspend fun login(username: String, pass: String): FrameworkResponse<LoginResponse> {
        return accountService.login(LoginRequest(username, pass)).body()!!
    }

    override suspend fun localLogin(username: String, pass: String): FrameworkResponse<LoginResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun localRegister(username: String, pass: String): FrameworkResponse<RegisterResponse> {
        TODO("Not yet implemented")
    }
}