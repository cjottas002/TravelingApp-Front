package es.travelworld.traveling.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.travelworld.traveling.data.repository.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: AccountRepository) : ViewModel() {
    private val invalidCharacters = arrayOf('@', '!', '#', '$')

    val name = MutableStateFlow("")
    val lastName = MutableStateFlow("")
    val isAdult = MutableStateFlow(false)
    val isNombreValid = MutableStateFlow(false)
    val isApellidosValid = MutableStateFlow(false)
    val isRegisterEnabled = MutableStateFlow(false)

    fun register(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (!isNombreValid.value) {
            onError("El nombre contiene caracteres no permitidos")
            return
        }
        if (!isApellidosValid.value) {
            onError("El apellido contiene caracteres no permitidos")
            return
        }
        if (name.value.isBlank() || lastName.value.isBlank()) {
            onError("Debes completar nombre y apellido")
            return
        }
        if (!isAdult.value) {
            onError("Debes ser mayor de edad")
            return
        }

        viewModelScope.launch {
            val response = repository.register(name.value, lastName.value)
            if(response.data != null)

            if(response.success && response.data.isRegistered) {
                onSuccess()
            }

            if (!response.success) {
                val msg = response.errors
                    .joinToString("\n") { it.message }
                onError(msg)
            }
        }
    }

    fun onNameOrLastNameChanged(nameInput: String, lastNameInput: String) {
        name.value = nameInput
        lastName.value = lastNameInput
        isNombreValid.value = isValidNameOrLastName(nameInput)
        isApellidosValid.value = isValidNameOrLastName(lastNameInput)
        validateAndEnableRegisterButton()
    }

    fun setIsAdult(adult: Boolean) {
        isAdult.value = adult
        validateAndEnableRegisterButton()
    }

    private fun isValidNameOrLastName(input: String): Boolean {
        return invalidCharacters.none { input.contains(it) }
    }

    private fun validateAndEnableRegisterButton() {
        isRegisterEnabled.value =
            isNombreValid.value && isApellidosValid.value &&
                    name.value.isNotEmpty() && lastName.value.isNotEmpty() && isAdult.value
    }
}