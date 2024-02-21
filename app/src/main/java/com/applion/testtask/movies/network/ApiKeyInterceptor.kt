package com.applion.testtask.movies.network

import com.applion.testtask.movies.Config
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder().addQueryParameter("api_key", Config.API_KEY).build()
        val request = chain.request().newBuilder().url(url).build();
        return chain.proceed(request)
    }
}