package com.test.unsplash.util

import com.test.unsplash.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Client-ID ${BuildConfig.ACCESS_KEY}")
            .build()
        return chain.proceed(request)
    }
}