package tech.needsbox.mobile.api.services

import retrofit2.http.GET
import retrofit2.http.Query
import tech.needsbox.mobile.api.model.misc.PaginatedResult
import tech.needsbox.mobile.api.model.services.Service

interface ServiceService {
    @GET("search/services/")
    suspend fun getServices(
        @Query("page") page: Int = 1,
        @Query("page") search: String? = null
    ): PaginatedResult<Service>
}