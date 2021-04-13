package com.hellobiz.mission.mission4.puttingtogether.page3.dialog.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.`interface`.Dialog
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.DialogModel
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogService(dialog : Dialog){

    private val mDialog : Dialog = dialog

    fun getDialogtService(memId:Int,memType:String,page:Int){
        val dialogInterface : Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

/*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */

        dialogInterface.getDialogData(memId,memType,page).enqueue(object :
            Callback<DialogModel?> {
            override fun onResponse(call: Call<DialogModel?>, response: Response<DialogModel?>)
            {
                val dialogModel : DialogModel? = response.body()
                val error : ResponseBody? = response.errorBody()
                if(dialogModel == null){
                    if(error != null) mDialog.dialogError(ErrorUtils.paresError(error))
                    else mDialog.dialogFailure(null)
                    return
                }
                mDialog.dialogSuccess(dialogModel)
            }

            override fun onFailure(call: Call<DialogModel?>, t: Throwable) {
                mDialog.dialogFailure(t)
            }
        })
    }

}