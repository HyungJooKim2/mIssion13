package com.hellobiz.mission.mission1.detailview.service

import com.hellobiz.mission.mission1.detailview.interfaces.DetailRetrofitInterface
import com.hellobiz.mission.mission1.detailview.interfaces.DetailView
import com.hellobiz.mission.mission1.detailview.model.MyDetailModel
import com.hellobiz.mission.mission1.GlobalApplication
import com.hellobiz.mission.error.ErrorUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailService(detailView: DetailView) {
    private val mDetailView: DetailView = detailView

    //GlobalApplication에 설정한 retrofit과 Interface 연결
    fun GetDetail(id: Int) {
        val detailRetrofitInterface: DetailRetrofitInterface =
            GlobalApplication.getRetrofit()!!.create(
                DetailRetrofitInterface::class.java
            )

        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */
        detailRetrofitInterface.getDetailData(id)?.enqueue(object : Callback<MyDetailModel?> {

            override fun onResponse(
                call: Call<MyDetailModel?>,
                response: Response<MyDetailModel?>
            ) {
                val MyDetailModel: MyDetailModel? = response.body()
                val error: ResponseBody? = response.errorBody()
                if (MyDetailModel == null) {
                    if (error != null) mDetailView.detailError(ErrorUtils.paresError(error))
                    else mDetailView.detailFailure(null)
                    return
                }
                mDetailView.detailSuccess(MyDetailModel)
            }

            override fun onFailure(call: Call<MyDetailModel?>, t: Throwable) {
                mDetailView.detailFailure(t)
            }
        }
        )
    }
}