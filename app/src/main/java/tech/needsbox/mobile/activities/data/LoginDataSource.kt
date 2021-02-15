package tech.needsbox.mobile.activities.data

import tech.needsbox.mobile.api.NeedsBoxClient
import tech.needsbox.mobile.api.model.auth.AuthTokenRequest
import tech.needsbox.mobile.api.model.users.NeedsBoxUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun loginWithCredentials(username: String, password: String): Result<NeedsBoxUser> {
        return try {
            val tokenResponse = NeedsBoxClient.userAuthService.createUserSession(
                AuthTokenRequest(
                    username,
                    password
                )
            )
            loginWithToken(tokenResponse?.token)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    suspend fun loginWithToken(token: String?): Result<NeedsBoxUser> {
        try {
            NeedsBoxClient.token =
                token ?: return Result.Error(Exception("Invalid credentials"))

            val currentUser =
                NeedsBoxClient.userAuthService.getSelfUser()?.also { it.token = token }
                    ?: return Result.Error(
                        Exception("Unable to get self user after logging-in!")
                    )
            return Result.Success(currentUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}