package com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model

import com.google.gson.annotations.SerializedName

data class GroupPatchModel(
    @SerializedName("code") var code: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: ArrayList<GroupPatchResponse>
)

data class GroupPatchResponse(
    @SerializedName("GRP_ID") var gprId: String,
    @SerializedName("GRP_NAME") var gprName: String,
    @SerializedName("GRP_PER") var gprPer: Int,
)

