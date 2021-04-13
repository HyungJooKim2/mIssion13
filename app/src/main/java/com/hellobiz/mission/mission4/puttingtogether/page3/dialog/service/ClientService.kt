package com.hellobiz.mission.mission4.puttingtogether.page3.dialog.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.`interface`.Client
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.ClientModel
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientService(client : Client){

    private val mClient : Client = client

    fun getCleintService(memId:Int,memType:String,srsId:Int,page:Int){
        val clientInterface : Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

/*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */

        clientInterface.getClientData(memId,memType,srsId,page).enqueue(object :
            Callback<ClientModel?> {
            override fun onResponse(call: Call<ClientModel?>, response: Response<ClientModel?>)
            {
                val clientModel : ClientModel? = response.body()
                val error : ResponseBody? = response.errorBody()
                if(clientModel == null){
                    if(error != null) mClient.clientError(ErrorUtils.paresError(error))
                    else mClient.clientFailure(null)
                    return
                }
                mClient.clientSuccess(clientModel)
            }

            override fun onFailure(call: Call<ClientModel?>, t: Throwable) {
                mClient.clientFailure(t)
            }
        })
    }

}