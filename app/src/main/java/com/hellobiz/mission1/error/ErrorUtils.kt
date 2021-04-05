package com.hellobiz.mission1.error

import com.google.gson.Gson
import com.hellobiz.mission1.error.model.ErrorRespose
import okhttp3.ResponseBody

object ErrorUtils {
    fun paresError(response : ResponseBody?): ErrorRespose{
        val gson = Gson()
        return gson.fromJson(
            response!!.charStream(),
            ErrorRespose::class.java
        )
    }
}