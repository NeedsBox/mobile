package tech.needsbox.mobile.api.model.misc

data class PaginatedResult<T>(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    var results: List<T>
)