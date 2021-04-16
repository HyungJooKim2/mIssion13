package com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.`interface`.GroupPatch
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupPatchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupPatchResponse
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GroupPatchService(groupPatch: GroupPatch) {

    private val mGroupPatch : GroupPatch = groupPatch

    fun getGroupPatchService(groupPatchPatch : GroupPatchResponse) {
        val groupPatchInterface: Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */

        groupPatchInterface.patchGroupData(groupPatchPatch).enqueue(object : Callback<GroupPatchModel?> {
                override fun onResponse(call: Call<GroupPatchModel?>, response: Response<GroupPatchModel?>) {
                    val groupPatchModel: GroupPatchModel? = response.body()
                    val error: ResponseBody? = response.errorBody()
                    if (groupPatchModel == null) {
                        if (error != null) mGroupPatch.groupPatchError(ErrorUtils.paresError(error))
                        else mGroupPatch.groupPatchFailure(null)
                        return
                    }
                    mGroupPatch.groupPatchSuccess(groupPatchModel)
                }

                override fun onFailure(call: Call<GroupPatchModel?>, t: Throwable) {
                    mGroupPatch.groupPatchFailure(t)
                }
            })
    }

}