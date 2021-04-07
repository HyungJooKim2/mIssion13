package com.hellobiz.mission.mission3.signup.model

import com.google.gson.annotations.SerializedName


data class PatchProfileBody (
    @SerializedName("MEM_ID") var memId : Int?,
    @SerializedName("MEM_NAME") var memName : String?,
    @SerializedName("MEM_TEL") var memTel: String?,
    @SerializedName("MEM_PASS") var memPass : String?,
    @SerializedName("MEM_EMAIL") var memEmail: String?,

)


data class SignUpModel(@SerializedName("code") var code: Int,
                       @SerializedName("message") var message: String
)

