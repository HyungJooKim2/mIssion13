package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.model

import com.google.gson.annotations.SerializedName

data class SearchModel(@SerializedName("code") var code: Int,
                       @SerializedName("message") var message: String,
                       @SerializedName("data") var data: ArrayList<SearchResponse>
)
data class SearchResponse(
    @SerializedName("MEM_ID") var memId : Int,
    @SerializedName("SRS_ID") var srsId : Int,
    @SerializedName("SRS_NM") var srsNm : String,
    @SerializedName("SRS_ADDR") var srsAddr : String,
    @SerializedName("SRS_TEL") var srsTel : String,
    @SerializedName("CNT_TYPE") var cntType : String
)
