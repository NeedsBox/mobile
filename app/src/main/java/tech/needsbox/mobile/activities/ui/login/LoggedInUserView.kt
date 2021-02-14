package tech.needsbox.mobile.activities.ui.login

import tech.needsbox.mobile.api.model.users.User

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val user: User,
    val displayName: String = user.name
    //... other data fields that may be accessible to the UI
)