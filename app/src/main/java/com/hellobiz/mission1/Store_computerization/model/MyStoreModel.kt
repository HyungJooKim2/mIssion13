package com.hellobiz.mission1.Store_computerization.model

import com.google.gson.annotations.SerializedName

data class MyStoreModel(
    @SerializedName("code") var code : Int,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : ArrayList<MyStoreResponse>,
)
data class MyStoreResponse(
    @SerializedName("READ_YN") var READ_YN : String,
    @SerializedName("ID") var ID : Int,
    @SerializedName("REQ_NAME") var REQ_NAME : String,
    @SerializedName("REQ_DT") var REQ_DT : String,
    @SerializedName("CLOSE_YN") var CLOSE_YN : String,
    @SerializedName("AREA_NM") var AREA_NM : String,
    @SerializedName("MEM_NICK") var MEM_NICK : String,
    @SerializedName("CATEGORY_CD") var CATEGORY_CD : String,
)