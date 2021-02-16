package tech.needsbox.mobile.api

import com.fasterxml.jackson.core.type.TypeReference
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

            val typeReference = object : TypeReference<PaginatedResult<T>>() {}
            val typeReferenceList = mapper.typeFactory.constructCollectionType(
                MutableList::class.java,
                T::class.java
            )

            println(typeReference.type)
            val node = mapper.writeValueAsString(it)
            val nodeResults = mapper.writeValueAsString(it.get("results"))

            val finalResult = mapper.readValue(
                node,
                typeReference
            )

            finalResult.apply {
                val newList = mapper.readValue<List<T>>(nodeResults, typeReferenceList)
                results = newList
            }
        }
    }

suspend inline fun <reified T> PaginatedResult<T>.getPreviousPage(): PaginatedResult<T>? {
    return previous?.let {
        getGenericResults(it)
    }
}