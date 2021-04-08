package com.hellobiz.mission.mission3.signup.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission3.signup.model.SignUpModel
import com.hellobiz.mission.mission3.signup.signupinterface.SignUp
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService(signUp: SignUp) {
    private val mSignUp: SignUp = signUp

    //GlobalApplication에 설정한 retrofit과 Interface 연결
    //memId:Int, memName:String, memTel:String, memPass:String, memEmail:String
    fun getSignUpService(memId: Int, patchProfileBody: Map<String, RequestBody>) {
        val signUpInterface: Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우  Success 함수를 호출, response.body()를 넘겨줌
        signUpInterface.getSignUpData(memId,memName,memTel,memPass,memEmail).enqueue(object : Callback<SignUpModel> {

        Map<String, PatchProfileBody>
        */
        signUpInterface.getSignUpData(memId, patchProfileBody)
            .enqueue(object : Callback<SignUpModel> {
                override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>) {
                    val signUpModel: SignUpModel? = response.body()
                    val error: ResponseBody? = response.errorBody()
                    if (signUpModel == null) {
                        if (error != null) mSignUp.signUpError(ErrorUtils.paresError(error))
                        else mSignUp.signUpFailure(null)
                        return
                    }
                    mSignUp.signUpSuccess(signUpModel)
                }

                override fun onFailure(call: Call<SignUpModel>, t: Throwable) {
                    mSignUp.signUpFailure(t)
                }
            })
    }
}