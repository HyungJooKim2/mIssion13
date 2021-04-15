package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.`interface`.ClientPatch
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.model.ClientPatchBody
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.model.ClientPatchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.ClientResponse
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.`interface`.GroupPatch
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupPatchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupResponse
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientPatchService(clientPatch: ClientPatch) {

    private val mClientPatch: ClientPatch = clientPatch

    fun getClientPatchService(clientPatch: ClientPatchBody) {
        val clientPatchInterface: Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */

        clientPatchInterface.patchClientData(clientPatch)
            .enqueue(object : Callback<ClientPatchModel?> {
                override fun onResponse(
                    call: Call<ClientPatchModel?>,
                    response: Response<ClientPatchModel?>
                ) {
                    val clientPatchModel: ClientPatchModel? = response.body()
                    val error: ResponseBody? = response.errorBody()
                    if (clientPatchModel == null) {
                        if (error != null) mClientPatch.clientPatchError(ErrorUtils.paresError(error))
                        else mClientPatch.clientPatchFailure(null)
                        return
                    }
                    mClientPatch.clientPatchSuccess(clientPatchModel)
                }

                override fun onFailure(call: Call<ClientPatchModel?>, t: Throwable) {
                    mClientPatch.clientPatchFailure(t)
                }
            })
    }
}


