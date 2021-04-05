package com.hellobiz.mission.store_computerization.service

import com.hellobiz.mission.mission1.GlobalApplication
import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.store_computerization.interfaces.MainActivityView
import com.hellobiz.mission.store_computerization.interfaces.MainRetrofitInterface
import com.hellobiz.mission.store_computerization.model.MyStoreModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService(mainActivityView: MainActivityView) {
    private val mMainActivityView : MainActivityView = mainActivityView

    //GlobalApplication에 설정한 retrofit과 Interface 연결
    fun GetMain(){
        val MainRetrofitInterface : MainRetrofitInterface =
            GlobalApplication.getRetrofit()!!.create(
                MainRetrofitInterface::class.java
            )
        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */
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