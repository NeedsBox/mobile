package tech.needsbox.mobile.activities.data

import tech.needsbox.mobile.api.NeedsBoxClient
import tech.needsbox.mobile.api.model.auth.AuthTokenRequest
import tech.needsbox.mobile.api.model.users.User
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<User> {
        try {
            val tokenResponse = NeedsBoxClient.userAuthService.createUserSession(
                AuthTokenRequest(
                    username,
                    password
                )
            )
            NeedsBoxClient.token = tokenResponse?.token ?: return Result.Error(Exception("Invalid credentials"))

            val currentUser = NeedsBoxClient.userAuthService.getSelfUser() ?: return Result.Error(Exception("Unable to get self user after logging-in!"))
            return Result.Success(currentUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}