package com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model

import com.google.gson.annotations.SerializedName

data class ClientModel (@SerializedName("code") var code: Int,
@SerializedName("message") var message: String,
@SerializedName("data") var data: ArrayList<ClientResponse>
)
data class ClientResponse(
    @SerializedName("CNT_NM") var cntNm : String,
    @SerializedName("CNT_ADDR") var cntAddr : String,
    @SerializedName("CNT_TEL") var cntTel : String,
    @SerializedName("CNT_TYPE") var cntType : String,
    @SerializedName("CNT_MEMO") var cntMemo : String
)
