package com.hellobiz.mission1.Store_computerization.service

import com.hellobiz.mission1.GlobalApplication
import com.hellobiz.mission1.Store_computerization.error.ErrorUtils
import com.hellobiz.mission1.Store_computerization.interfaces.MainActivityView
import com.hellobiz.mission1.Store_computerization.interfaces.MainRetrofitInterface
import com.hellobiz.mission1.Store_computerization.model.MyStoreModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService(mainActivityView: MainActivityView) {
    private val mMainActivityView : MainActivityView = mainActivityView

    fun GetMain(){
        val MainRetrofitInterface : MainRetrofitInterface =
            GlobalApplication.getRetrofit()!!.create(
                MainRetrofitInterface::class.java
            )

        MainRetrofitInterface.GetStoreData().enqueue(object : Callback<MyStoreModel>{
            override fun onResponse(call: Call<MyStoreModel>, response: Response<MyStoreModel>)
            {
                val MyStoreModel : MyStoreModel? = response.body()
                val error : ResponseBody? = response.errorBody()
                if(MyStoreModel == null){
                    if(error != null) mMainActivityView.MainError(ErrorUtils.paresError(error))
                    else mMainActivityView.MainFailure(null)
                    return
                }
                mMainActivityView.MainSuccess(MyStoreModel)
            }

            override fun onFailure(call: Call<MyStoreModel>, t: Throwable) {
                mMainActivityView.MainFailure(t)
            }
        })
    }
}