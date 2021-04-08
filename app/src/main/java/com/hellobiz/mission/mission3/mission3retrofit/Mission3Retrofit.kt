package com.hellobiz.mission.mission3.mission3retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Mission3Retrofit {
    companion object {
        val BASE_URL = "https://api.ydnjs.com/"

        private var retrofit: Retrofit? = null

        @JvmName("getRetrofit")
        fun getRetrofit(): Retrofit? {

            if (retrofit == null) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val client = OkHttpClient.Builder()
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .writeTimeout(10000, TimeUnit.MILLISECONDS)
                    .addInterceptor(loggingInterceptor) //for test
                    .build()

                //.addInterceptor(SupportInterceptor())

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit
        }
    }
}