package tech.needsbox.mobile.activities.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.needsbox.mobile.R
import tech.needsbox.mobile.activities.data.LoginRepository
import tech.needsbox.mobile.activities.data.Result

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun attemptPreviousLogin(): Boolean {
        if (loginRepository.storedPreviousToken != null) {
            viewModelScope.launch {
                innerLogin("NeedsBox", "NeedsBox", true)
            }
            return true
        }
        return false
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            innerLogin(username, password)
        }
    }

    private suspend fun innerLogin(
        username: String,
        password: String,
        isTokenAuth: Boolean = false
    ) {
        val result = loginRepository.login(username, password, isTokenAuth)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = result.data)
        } else {
            _loginResult.value =
                LoginResult(error = if (isTokenAuth) R.string.login_token_failed else R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 8
    }
}