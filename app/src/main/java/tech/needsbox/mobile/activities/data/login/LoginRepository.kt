package tech.needsbox.mobile.activities.data.login

import tech.needsbox.mobile.activities.data.Result
import tech.needsbox.mobile.api.model.users.NeedsBoxUser
import kotlin.reflect.KMutableProperty

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(private val tokenProp: KMutableProperty<String?>, val dataSource: LoginDataSource) {

    var storedPreviousToken: String?
        get() = tokenProp.getter.call()
        set(value) {
            tokenProp.setter.call(value)
        }

    // in-memory cache of the loggedInUser object
    var user: NeedsBoxUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    suspend fun login(username: String, password: String, isTokenAuth: Boolean = false): Result<NeedsBoxUser> {
        val previousToken = storedPreviousToken
        // handle login
        val result = if (isTokenAuth && previousToken != null) {
            dataSource.loginWithToken(storedPreviousToken)
        } else {
            storedPreviousToken = null
            dataSource.loginWithCredentials(username, password)
        }

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: NeedsBoxUser) {
        this.user = loggedInUser
        storedPreviousToken = loggedInUser.token
    }

}