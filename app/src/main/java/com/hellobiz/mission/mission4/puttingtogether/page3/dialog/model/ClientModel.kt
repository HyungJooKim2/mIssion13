package com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model

import com.google.gson.annotations.SerializedName

data class ClientModel (@SerializedName("code") var code: Int,
@SerializedName("message") var message: String,
@SerializedName("data") var data: ArrayList<ClientResponse>
)
data class ClientResponse(
    @SerializedName("CNT_ID") var cntId : Int,
    @SerializedName("MEM_ID") var memId : Int,
    @SerializedName("SRS_ID") var srsId : Int,
    @SerializedName("CNT_SRS_ID") var cntSrsId : Int,
    @SerializedName("CNT_PRC_GR_CD") var cntPrcGrCd : Int,
    @SerializedName("CNT_NM") var cntNm : String,
    @SerializedName("CNT_ADDR") var cntAddr : String,
    @SerializedName("CNT_TEL") var cntTel : String,
    @SerializedName("CNT_TYPE") var cntType : String,
    @SerializedName("CNT_TYPE_NM") var cntTypeNm : String,
    @SerializedName("CNT_MEMO") var cntMemo : String
)
