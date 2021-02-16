package tech.needsbox.mobile.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.needsbox.mobile.api.NeedsBoxClient.mapper
import tech.needsbox.mobile.api.model.misc.PaginatedResult

suspend inline fun <reified T> PaginatedResult<T>.getNextPage(): PaginatedResult<T>? {
    return next?.let {
        getGenericResults(it)
    }
}

suspend inline fun <reified T> getGenericResults(it: String): PaginatedResult<T>? =
    NeedsBoxClient.miscService.getGenericResults(
        it
    ).let {

        withContext(Dispatchers.IO) {

            val node = mapper.writeValueAsString(it)

            mapper.readValue(
                node,
                mapper.typeFactory.constructSimpleType(
                    PaginatedResult::class.java,
                    arrayOf(mapper.typeFactory.constructSimpleType(T::class.java, emptyArray()))
                )
            )
        }
    }

suspend inline fun <reified T> PaginatedResult<T>.getPreviousPage(): PaginatedResult<T>? {
    return previous?.let {
        getGenericResults(it)
    }
}