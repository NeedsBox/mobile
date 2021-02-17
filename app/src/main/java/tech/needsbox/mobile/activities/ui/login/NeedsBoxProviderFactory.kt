package tech.needsbox.mobile.activities.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.needsbox.mobile.NeedsBoxApplication
import tech.needsbox.mobile.activities.data.login.LoginDataSource
import tech.needsbox.mobile.activities.data.login.LoginRepository
import tech.needsbox.mobile.activities.data.main.MainViewModel
import tech.needsbox.mobile.api.NeedsBoxClient

class NeedsBoxProviderFactory(private val application: NeedsBoxApplication) : ViewModelProvider.Factory {
    private val loginRepository = LoginRepository(
        NeedsBoxClient::token, LoginDataSource()
    )

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = loginRepository
            ) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(loginRepository.user ?: throw Exception("Attempt to access main activity without being logged-in.")) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}