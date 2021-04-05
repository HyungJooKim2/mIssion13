package com.hellobiz.mission1.detailview.model

import com.google.gson.annotations.SerializedName

data class MyDetailModel(
    @SerializedName("code") var code : Int,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : ArrayList<MyDetailResponse>,
)
data class MyDetailResponse(
    @SerializedName("RAQ_BRAND_NM") var RAQ_BRAND_NM : String,
    @SerializedName("RAQ_CAR_NM") var RAQ_CAR_NM : String,
    @SerializedName("MEM_NICK") var MEM_NICK : String,
    @SerializedName("RAQ_YEARS") var RAQ_YEARS : String,
    @SerializedName("RAQ_DISTANCE") var RAQ_DISTANCE : String,
    @SerializedName("RAQ_AREA") var RAQ_AREA : String,
    @SerializedName("RAQ_REPAIR") var RAQ_REPAIR : String,
    @SerializedName("RAQ_INSURE_TYPE_NM") var RAQ_INSURE_TYPE_NM : String,
)