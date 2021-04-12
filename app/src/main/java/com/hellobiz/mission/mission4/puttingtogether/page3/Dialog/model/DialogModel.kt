package com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.model

import com.google.gson.annotations.SerializedName

data class DialogModel(
    @SerializedName("code") var code: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: ArrayList<DialogResponse>
)
data class DialogResponse(
    @SerializedName("SRS_ID") var srsId : Int,
    @SerializedName("SRS_NM") var srsNm : String,
    @SerializedName("SRS_ADDR1") var srsAddr1 : String,
    @SerializedName("SRS_ADDR2") var srsAddr2 : String,
)
