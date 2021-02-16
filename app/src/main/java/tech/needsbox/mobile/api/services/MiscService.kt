package tech.needsbox.mobile.api.services

import com.fasterxml.jackson.databind.JsonNode
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import tech.needsbox.mobile.api.model.misc.Category
import tech.needsbox.mobile.api.model.misc.PaginatedResult

interface MiscService {
    @GET("categories/")
    suspend fun getCategories(@Query("page") pageNumber: Int = 1): PaginatedResult<Category>

    @GET
    suspend fun getGenericResults(
        @Url url: String,
    ): JsonNode
}