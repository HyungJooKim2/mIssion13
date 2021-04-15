package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.model

import com.google.gson.annotations.SerializedName

data class ClientPatchModel(
    @SerializedName("code") var code: Int,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: ArrayList<ClientPatchBody>
)

data class ClientPatchBody(
    @SerializedName("CNT_ID") var cntId: Int,
    @SerializedName("MEM_ID") var memId: Int,
    @SerializedName("MEM_TYPE") var memType: String,
    @SerializedName("SRS_ID") var srsId: Int,
    @SerializedName("CNT_SRS_ID") var cntSrsId: Int,
    @SerializedName("CNT_TYPE") var cntType: String,
    @SerializedName("CNT_PRC_GR_CD") var cntPrcGrCd: Int,
    @SerializedName("CNT_MEMO") var cntMemo: String
)

