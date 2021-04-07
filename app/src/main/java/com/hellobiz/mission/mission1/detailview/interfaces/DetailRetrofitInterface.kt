package com.hellobiz.mission.mission1.detailview.interfaces


import com.hellobiz.mission.mission1.detailview.model.MyDetailModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DetailRetrofitInterface {

    /**
     상세화면 정보 가져오는 api
     @params RAQ_ID : Main화면 get api를 통해 받아온 해당 id값
     @return 단순 응답 바디를 반환값으로 받는 Call
     **/
@GET("api/repair/GetEatimationRepair?")
fun getDetailData(@Query("RAQ_ID") id:Int?) : Call<MyDetailModel?>?

}