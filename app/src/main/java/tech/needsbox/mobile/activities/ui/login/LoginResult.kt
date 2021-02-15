package tech.needsbox.mobile.activities.ui.login

import tech.needsbox.mobile.api.model.users.NeedsBoxUser

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: NeedsBoxUser? = null,
    val error: Int? = null
)