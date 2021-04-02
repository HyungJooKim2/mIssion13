package com.hellobiz.mission1.store_computerization.interfaces


import com.hellobiz.mission1.store_computerization.model.MyStoreModel
import retrofit2.Call
import retrofit2.http.GET

interface MainRetrofitInterface {
    /**
    메인화면 정보 가져오는 api
    @return 단순 응답 바디를 반환값으로 받는 Call
     **/
@GET("api/estimation/EstiReceivedPartnerListRepairAccomp?PTR_ID=2099&PAGE=1")
fun GetStoreData() : Call<MyStoreModel>
}