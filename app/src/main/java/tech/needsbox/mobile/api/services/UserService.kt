package tech.needsbox.mobile.api.services

import tech.needsbox.mobile.api.model.users.NeedsBoxUser
import retrofit2.http.POST

interface UserService {

    @POST("/users/")
    suspend fun createUser(newUser: NeedsBoxUser): NeedsBoxUser

}