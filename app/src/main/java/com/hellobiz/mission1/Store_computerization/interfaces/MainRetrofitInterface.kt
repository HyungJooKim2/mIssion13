package com.hellobiz.mission1.Store_computerization.interfaces


import com.hellobiz.mission1.Store_computerization.model.MyStoreModel
import retrofit2.Call
import retrofit2.http.GET

interface MainRetrofitInterface {

@GET("api/estimation/EstiReceivedPartnerListRepairAccomp?PTR_ID=2099&PAGE=1")
fun GetStoreData() : Call<MyStoreModel>
}