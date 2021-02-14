package tech.needsbox.mobile.api.services

import tech.needsbox.mobile.api.model.auth.AuthTokenRequest
import tech.needsbox.mobile.api.model.auth.AuthTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import tech.needsbox.mobile.api.model.users.User

interface UserAuthService {
    @POST("users/sessions")
    suspend fun createUserSession(
        @Body
        request: AuthTokenRequest
    ): AuthTokenResponse?

    @GET("users/self")
    suspend fun getSelfUser(): User?
}