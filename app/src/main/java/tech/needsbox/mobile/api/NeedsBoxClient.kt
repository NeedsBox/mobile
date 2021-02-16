package tech.needsbox.mobile.api

import androidx.core.content.edit
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import tech.needsbox.mobile.api.services.UserAuthService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create
import tech.needsbox.mobile.NeedsBoxApplication
import tech.needsbox.mobile.api.services.MiscService

object NeedsBoxClient {

    private const val needsBoxHost = "192.168.50.100:8000"

    private const val tokenKey = "needsbox-token"

    var token: String?
        get() = NeedsBoxApplication.INSTANCE.preferences.getString(tokenKey, null)
        set(value) {
            NeedsBoxApplication.INSTANCE.preferences.edit {
                this.putString(tokenKey, value)
            }
        }

    val mapper = jacksonObjectMapper()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://$needsBoxHost/api/")
        .client(OkHttpClient.Builder().addInterceptor(UserTokenInterceptor).build())
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .build()

    val userAuthService: UserAuthService = retrofit.create()

    val miscService: MiscService = retrofit.create()
}
