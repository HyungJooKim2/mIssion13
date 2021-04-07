package com.hellobiz.mission.mission3.signup.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission3.signup.model.ModificationModel
import com.hellobiz.mission.mission3.signup.model.SignUpModel
import com.hellobiz.mission.mission3.signup.signupinterface.EmployeeModification
import com.hellobiz.mission.mission3.signup.signupinterface.SignUp
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModificationService (modification : EmployeeModification){
    private val mModification : EmployeeModification = modification

    //GlobalApplication에 설정한 retrofit과 Interface 연결
    fun getModificationService(sffId:Int, sffName:String, srsTel:String, sffEmail:String, sffPass:String ){
        val modificationInterface : Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */
        modificationInterface.getModificationData(sffId,sffName,srsTel,sffEmail,sffPass).enqueue(object :
            Callback<ModificationModel> {
            override fun onResponse(call: Call<ModificationModel>, response: Response<ModificationModel>)
            {
                val signUpModel : ModificationModel? = response.body()
                val error : ResponseBody? = response.errorBody()
                if(signUpModel == null){
                    if(error != null) mModification.modificationError(ErrorUtils.paresError(error))
                    else mModification.modificationFailure(null)
                    return
                }
                mModification.modificationSuccess(signUpModel)
            }

            override fun onFailure(call: Call<ModificationModel>, t: Throwable) {
                mModification.modificationFailure(t)
            }
        })
    }
}