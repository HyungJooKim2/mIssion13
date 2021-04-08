package com.hellobiz.mission.mission3.signup.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission3.signup.model.ProfileUpdateModel
import com.hellobiz.mission.mission3.signup.signupinterface.ProfileUpdate
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ProfileUpdateService(profileUpdate: ProfileUpdate) {
    private val mProfileUpdate: ProfileUpdate = profileUpdate

    //GlobalApplication에 설정한 retrofit과 Interface 연결
    fun getProfileUpdateService(memId: Int, patchProfileBody: Map<String, RequestBody>,memImg: File) {
        val profileUpdateInterface: Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우  Success 함수를 호출, response.body()를 넘겨줌
        */

        profileUpdateInterface.getSignUpData(memId, patchProfileBody,memImg)
            .enqueue(object : Callback<ProfileUpdateModel> {
                override fun onResponse(call: Call<ProfileUpdateModel>, response: Response<ProfileUpdateModel>) {
                    val profileUpdateModel: ProfileUpdateModel? = response.body()
                    val error: ResponseBody? = response.errorBody()
                    if (profileUpdateModel == null) {
                        if (error != null) mProfileUpdate.profileUpdateError(ErrorUtils.paresError(error))
                        else mProfileUpdate.profileUpdateFailure(null)
                        return
                    }
                    mProfileUpdate.profileUpdateSuccess(profileUpdateModel)
                }

                override fun onFailure(call: Call<ProfileUpdateModel>, t: Throwable) {
                    mProfileUpdate.profileUpdateFailure(t)
                }
            })
    }
}