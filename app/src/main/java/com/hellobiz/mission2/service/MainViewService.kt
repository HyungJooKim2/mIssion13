package com.hellobiz.mission2.mainview.service

import com.hellobiz.mission1.error.ErrorUtils
import com.hellobiz.mission2.mainview.GlobalApplication2
import com.hellobiz.mission2.mainview.maininterface.MainView
import com.hellobiz.mission2.mainview.maininterface.MainViewRetrofitInterface
import com.hellobiz.mission2.mainview.model.MainViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewService(mainView: MainView) {
    private val mMainView : MainView = mainView

    //GlobalApplication에 설정한 retrofit과 Interface 연결
    fun getMainView(){
        val mainViewRetrofitInterface : MainViewRetrofitInterface =
            GlobalApplication2.getRetrofit()!!.create(
                MainViewRetrofitInterface::class.java
            )
        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */
        mainViewRetrofitInterface.GetMainViewData().enqueue(object : Callback<MainViewModel>{
            override fun onResponse(call: Call<MainViewModel>, response: Response<MainViewModel>)
            {
                val mainViewModel : MainViewModel? = response.body()
                val error : ResponseBody? = response.errorBody()
                if(mainViewModel == null){
                    if(error != null) mMainView.mainViewError(ErrorUtils.paresError(error))
                    else mMainView.mainViewFailure(null)
                    return
                }
                mMainView.mainViewSuccess(mainViewModel)
            }

            override fun onFailure(call: Call<MainViewModel>, t: Throwable) {
                mMainView.mainViewFailure(t)
            }
        })
    }
}