package com.hellobiz.mission.mission3.signup.model

import com.google.gson.annotations.SerializedName

data class ModificationModel(
    @SerializedName("code") var code: Int,
    @SerializedName("message") var message: String
)


