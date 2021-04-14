package com.hellobiz.mission.mission4.puttingtogether.page3.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission4.puttingtogether.page3.`interface`.GroupInterface
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupModel
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GroupService(groupInterface: GroupInterface) {

    private val mGroupInterface: GroupInterface = groupInterface

    fun getManagementService(srsId: Int, memId: Int, page: Int) {
        val managementInterface: Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */

        managementInterface.getManagementData(srsId, memId, page)
            .enqueue(object : Callback<GroupModel?> {
                override fun onResponse(call: Call<GroupModel?>, response: Response<GroupModel?>) {
                    val groupModel: GroupModel? = response.body()
                    val error: ResponseBody? = response.errorBody()
                    if (groupModel == null) {
                        if (error != null) mGroupInterface.myGroupError(ErrorUtils.paresError(error))
                        else mGroupInterface.myGroupFailure(null)
                        return
                    }
                    mGroupInterface.myGroupSuccess(groupModel)
                }

                override fun onFailure(call: Call<GroupModel?>, t: Throwable) {
                    mGroupInterface.myGroupFailure(t)
                }
            })
    }

}
