package com.hellobiz.mission.mission1.detailview.model

import com.google.gson.annotations.SerializedName

data class MyDetailModel(
    @SerializedName("code") var code : Int,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : ArrayList<MyDetailResponse>,
)
data class MyDetailResponse(
    @SerializedName("RAQ_BRAND_NM") var raqBrandNm : String,
    @SerializedName("RAQ_CAR_NM") var raqCarNm : String,
    @SerializedName("MEM_NICK") var memNick : String,
    @SerializedName("RAQ_YEARS") var raqYears : String,
    @SerializedName("RAQ_DISTANCE") var raqDistance : String,
    @SerializedName("RAQ_AREA") var raqArea : String,
    @SerializedName("RAQ_REPAIR") var raqRepair : String,
    @SerializedName("RAQ_INSURE_TYPE_NM") var raqInsureTypeNm : String,
)