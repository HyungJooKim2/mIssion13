package com.hellobiz.mission.mission4.puttingtogether.mission3.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission4.puttingtogether.mission3.`interface`.Management
import com.hellobiz.mission.mission4.puttingtogether.mission3.model.ManagementModel
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagementService(management : Management){

    private val mManagement : Management = management

    fun getManagementService(srsId:Int,memId:Int,page:Int){
        val managementInterface : Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

/*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */

        managementInterface.getManagementData(srsId,memId,page).enqueue(object : Callback<ManagementModel?> {
            override fun onResponse(call: Call<ManagementModel?>, response: Response<ManagementModel?>)
            {
                val managementModel : ManagementModel? = response.body()
                val error : ResponseBody? = response.errorBody()
                if(managementModel == null){
                    if(error != null) mManagement.managementError(ErrorUtils.paresError(error))
                    else mManagement.managementFailure(null)
                    return
                }
                mManagement.managementSuccess(managementModel)
            }

            override fun onFailure(call: Call<ManagementModel?>, t: Throwable) {
                mManagement.managementFailure(t)
            }
        })
    }

}