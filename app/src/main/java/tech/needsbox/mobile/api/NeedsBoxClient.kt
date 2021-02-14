package tech.needsbox.mobile.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import tech.needsbox.mobile.api.services.UserAuthService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create

object NeedsBoxClient {

    private const val needsBoxHost = "192.168.50.100:8000"

    var token: String? = null
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://$needsBoxHost/api/")
        .client(OkHttpClient.Builder().addInterceptor(UserTokenInterceptor).build())
        .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
        .build()

    val userAuthService: UserAuthService = retrofit.create()

}