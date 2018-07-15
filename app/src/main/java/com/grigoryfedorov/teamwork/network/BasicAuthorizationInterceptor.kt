package com.grigoryfedorov.teamwork.network

import com.grigoryfedorov.teamwork.services.encoders.Encoder
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthorizationInterceptor(
        private val apiKeyProvider: ApiKeyProvider,
        private val encoder: Encoder
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val encodedApiKey = encoder.encode(apiKeyProvider.getApiKey())

        val request = chain.request()
        val authRequest = request.newBuilder()
                .header("Authorization", "BASIC $encodedApiKey")
                .build()
        return chain.proceed(authRequest)

    }
}
