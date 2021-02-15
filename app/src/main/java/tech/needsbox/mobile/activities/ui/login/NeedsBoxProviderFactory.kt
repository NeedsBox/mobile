package tech.needsbox.mobile.activities.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.needsbox.mobile.NeedsBoxApplication
import tech.needsbox.mobile.activities.data.LoginDataSource
import tech.needsbox.mobile.activities.data.LoginRepository
import tech.needsbox.mobile.activities.ui.main.ui.main.MainViewModel

const val tokenKey = "needsbox-token"

class NeedsBoxProviderFactory(private val application: NeedsBoxApplication) : ViewModelProvider.Factory {
    private val loginRepository = LoginRepository(
        ::token, LoginDataSource()
    )

    var token: String?
        get() = application.preferences.getString(tokenKey, null)
        set(value) {
            application.preferences.edit {
                this.putString(tokenKey, value)
            }
        }

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