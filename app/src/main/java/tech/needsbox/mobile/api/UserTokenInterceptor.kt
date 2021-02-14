package tech.needsbox.mobile.api

import okhttp3.Interceptor
import okhttp3.Response

object UserTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .apply {
                if (NeedsBoxClient.token != null) {
                    header("Authorization", "Token ${NeedsBoxClient.token}")
                }
            }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}