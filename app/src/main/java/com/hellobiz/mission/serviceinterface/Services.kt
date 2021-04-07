package com.hellobiz.mission.serviceinterface

import com.hellobiz.mission.mission3.signup.model.SignUpModel
import com.hellobiz.mission2.mainview.model.MainViewModel
import retrofit2.Call
import retrofit2.http.*

interface Services {

    /**
    메인화면 정보 가져오는 api
    @return 단순 응답 바디를 반환값으로 받는 Call
     **/
    @GET("api/truck/list/GetTruckList?MCR_LCD=&MCR_PRICE&MHT_USR_ID=4&MHT_USR_TYPE=DAL&MCR_SCD=&MCR_SCD_SNM&MCR_YEAR_MM_START=&MCR_YEAR_MM_END=&PAGE=3")
    fun GetMainViewData(): Call<MainViewModel>

    /**
    메인화면 정보 가져오는 api
    @return 단순 응답 바디를 반환값으로 받는 Call
     **/
    @GET("api/truck/list/GetTruckDealerList?DAL_ID=4&PAGE=1")
    fun getMyVehicleData(): Call<MainViewModel>

    @FormUrlEncoded
    @PATCH("api/user/UpdateProfile")
    fun getSignUpData(
        @Field("MEM_ID") memId: Int,
        @Field("MEM_NAME") memName: String,
        @Field("MEM_TEL") memTel: String,
        @Field("MEM_PASS") memPass: String,
        @Field("MEM_EMAIL") memEmail: String
    ): Call<SignUpModel>
}

