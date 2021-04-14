package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.service

import com.hellobiz.mission.error.ErrorUtils
import com.hellobiz.mission.mission3.mission3retrofit.Mission3Retrofit
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.`interface`.Search
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.model.SearchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.`interface`.GroupPatch
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupPatchModel
import com.hellobiz.mission.serviceinterface.Services
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService(search: Search) {

    private val mSearch: Search = search

    fun getSearchService(cntType: String, keyWord: String, page: Int, srsId: Int) {
        val getSearchInterface: Services =
            Mission3Retrofit.getRetrofit()!!.create(
                Services::class.java
            )

        /*
        enqueue()를 사용하여 서버와 통신했을때 콜백을 작성,
        Error가 나지 않을 경우 Success 함수를 호출, response.body()를 넘겨줌
        */

        getSearchInterface.getSearchData(cntType, keyWord, page, srsId).enqueue(object :
            Callback<SearchModel?> {
            override fun onResponse(
                call: Call<SearchModel?>,
                response: Response<SearchModel?>
            ) {
                val searchModel: SearchModel? = response.body()
                val error: ResponseBody? = response.errorBody()
                if (searchModel == null) {
                    if (error != null) mSearch.searchError(ErrorUtils.paresError(error))
                    else mSearch.searchFailure(null)
                    return
                }
                mSearch.searchSuccess(searchModel)
            }

            override fun onFailure(call: Call<SearchModel?>, t: Throwable) {
                mSearch.searchFailure(t)
            }
        })
    }

}