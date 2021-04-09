package com.hellobiz.mission.store_computerization.model

import com.google.gson.annotations.SerializedName

data class MyStoreModel(
    @SerializedName("code") var code : Int,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : ArrayList<MyStoreResponse>,
)

data class MyStoreResponse(
    @SerializedName("READ_YN") var readYn : String,
    @SerializedName("ID") var id : Int,
    @SerializedName("REQ_NAME") var reqName : String,
    @SerializedName("REQ_DT") var reqDt : String,
    @SerializedName("CLOSE_YN") var closeYn : String,
    @SerializedName("AREA_NM") var areaNm : String,
    @SerializedName("MEM_NICK") var memNick : String,
    @SerializedName("CATEGORY_CD") var categoryCd : String,
)