package com.hellobiz.mission2.mainview.model

import com.google.gson.annotations.SerializedName

data class MainViewModel(
    @SerializedName("code") var code : Int,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : ArrayList<MainViewResponse>,
)

data class MainViewResponse(
    @SerializedName("MCR_ID") var mcrId : Int,
    @SerializedName("MCR_IMG1") var mcrImg1 : String,
    @SerializedName("MCR_BRAND_NM") var mcrBrandNm : String,
    @SerializedName("MCR_MODEL_NM") var mcrModelNm : String,
    @SerializedName("MCR_LCD_NM") var mcrLcdNm : String,
    @SerializedName("MCR_TON") var mcrTon : Int,
    @SerializedName("MCR_ADDR1_NM") var mcrAddr1Nm : String,
    @SerializedName("MCR_ADDR2_NM") var mcrAddr2Nm : String,
    @SerializedName("MCR_KM") var mcrKm : Int,
    @SerializedName("MCR_PRICE") var mcrPrice : Int,
)