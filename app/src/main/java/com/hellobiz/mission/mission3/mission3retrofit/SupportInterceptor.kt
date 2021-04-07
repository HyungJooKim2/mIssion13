package com.hellobiz.mission.mission3.mission3retrofit

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

//https://stackoverflow.com/questions/62414663/unexpected-char-0x20-at-3-in-header-name-retrofit-kotlin
class SupportInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type","application/json")
            .build()
        return chain.proceed(request)

        //    .addHeader("Accept", "application/json")
    }
}