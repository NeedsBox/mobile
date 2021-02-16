package tech.needsbox.mobile.api.model.services

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import kotlinx.coroutines.runBlocking
import tech.needsbox.mobile.api.NeedsBoxClient
import tech.needsbox.mobile.api.model.misc.Category
import java.util.concurrent.TimeUnit

class CategoryByIdDeserializer : StdDeserializer<Category>(Category::class.java) {
    private val categoryCache: LoadingCache<Int, Category> = CacheBuilder.newBuilder()
        .expireAfterAccess(5, TimeUnit.MINUTES)
        .build(CacheLoader.from { id ->
            runBlocking { NeedsBoxClient.miscService.getCategory(id!!) }
        })

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Category {
        return categoryCache.get(p.intValue)
    }
}
