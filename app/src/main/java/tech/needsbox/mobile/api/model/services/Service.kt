package tech.needsbox.mobile.api.model.services

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import tech.needsbox.mobile.api.model.misc.Category
import tech.needsbox.mobile.api.model.users.NeedsBoxUser
import java.time.Instant

data class Service(
    val id: Int,
    val title: String,
    val description: String,
    @JsonDeserialize(using = CategoryByIdDeserializer::class) val category: Category,
    val location: String,
    @JsonProperty("created_at") val createdAt: Instant,
    val user: NeedsBoxUser
)