package tech.needsbox.mobile.api.model.users

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class NeedsBoxUser(
    val username: String,
    val name: String,
    val biography: String?,
    val contact: String?,
    val website: String?,
    @JsonProperty("account_type") val accountType: AccountType? = AccountType.PES,

    //Only used for account creation
    val email: String?,
    val password: String?,

    @JsonIgnore
    var token: String? = null
)