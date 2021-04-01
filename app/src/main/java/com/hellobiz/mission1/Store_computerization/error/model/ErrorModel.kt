package com.hellobiz.mission1.Store_computerization.error.model

import com.google.gson.annotations.SerializedName

data class ErrorRespose(
    @SerializedName("status") var status : Int,
    @SerializedName("success") var success : Boolean,
    @SerializedName("message") var message : String
)