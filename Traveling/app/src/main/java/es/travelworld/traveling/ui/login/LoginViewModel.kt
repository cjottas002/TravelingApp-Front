package es.travelworld.traveling.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.travelworld.traveling.core.auth.TokenManager
import es.travelworld.traveling.data.repository.AccountRepository
import es.travelworld.traveling.data.remote.User
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AccountRepository, private val tokenManager: TokenManager) : ViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isLoginEnabled = MutableLiveData<Boolean>()

    fun initializeLoginUser(username: String, password: String) {
        this.username.value = username
        this.password.value = password
        isLoginEnabled.value = username.isNotBlank() && password.isNotBlank()
    }

    fun login(onSuccess: (token: String) -> Unit, onError: (String) -> Unit) {
        val username = username.value.orEmpty()
        val pass = password.value.orEmpty()

        if (!isValidCredentials(username, pass)) {
            onError("Username or password cannot be empty.")
            return
        }

        viewModelScope.launch {
            val response = repository.login(username, pass)
            if(response.data != null && response.success){
                val token = response.data.token
                tokenManager.saveToken(token)
                onSuccess(token)
            } else {
                val msg = response.errors
                    .joinToString("\n") { it.message }
                onError(msg)
            }
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}