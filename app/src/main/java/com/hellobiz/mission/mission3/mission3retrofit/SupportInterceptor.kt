package com.hellobiz.mission.mission3.mission3retrofit

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

//https://stackoverflow.com/questions/62414663/unexpected-char-0x20-at-3-in-header-name-retrofit-kotlin

//Content-Type 을 바꿔주어야 할 때 Interceptor 를 통해 일괄적으로 바꿔 주는 클래스
class SupportInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type","multipart/form-data")
            .build()
        return chain.proceed(request)

        //    .addHeader("Accept", "application/json")
    }
}