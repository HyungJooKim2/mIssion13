package com.hellobiz.mission.mission4.puttingtogether.mission3.model

import com.google.gson.annotations.SerializedName

data class ManagementModel(
    @SerializedName("code") var code: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: ArrayList<ManagementResponse>
)

data class ManagementResponse(
    @SerializedName("GRP_ID") var gprId: Int,
    @SerializedName("GRP_NAME") var gprName: String,
    @SerializedName("GRP_PER") var gprPer: Int,
)


