package com.hellobiz.mission.serviceinterface

import com.hellobiz.mission.mission3.signup.model.ModificationModel
import com.hellobiz.mission.mission3.signup.model.ProfileUpdateModel
import com.hellobiz.mission2.mainview.model.MainViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface Services {

    /**
    메인화면 정보 가져오는 api
    @return 단순 응답 바디를 반환값으로 받는 Call
     **/
    @GET("api/truck/list/GetTruckList?MCR_LCD=&MCR_PRICE&MHT_USR_ID=4&MHT_USR_TYPE=DAL&MCR_SCD=&MCR_SCD_SNM&MCR_YEAR_MM_START=&MCR_YEAR_MM_END=&PAGE=3")
    fun GetMainViewData(): Call<MainViewModel>

    /**
    프로필을 업데이트 하는 api
    @params MEM_ID : get api를 통해 받아온 id값
    PatchProfileBody :{
    @params MEM_NAME : 사용자가 입력한 name
    @params MEM_TEL : 사용자가 입력한 tel
    @params MEM_PASS : 사용자가 입력한 pw
    @params MEM_EMAIL : 사용자가 입력한 email
    @return 단순 응답 바디를 반환값으로 받는 Call

    @Multipart : Multipart 말 그대로 메시지(=파일)를 여러 파트로 나누어서 메세지를 전달하는 방식
    @JvmSuppressWildcards : 코틀린 컴파일러는 제네릭을 기본적으로 Map<String>을 Map<? extends String>
                            형태로 변환합니다. 이런형태의 자동변환을 방지하는 방법
    }
     **/
    @GET("api/truck/list/GetTruckDealerList?DAL_ID=4&PAGE=1")
    fun getMyVehicleData(): Call<MainViewModel>

    @Multipart
    @PATCH("api/user/UpdateProfile")
    fun getSignUpData(
        @Part("MEM_ID") memID: Int,
        @PartMap partMap: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part("MEM_IMG") memImg:File
    ): Call<ProfileUpdateModel>

    /*
       @Multipart
    @PATCH("api/user/UpdateProfile")
    fun getSignUpData(
        @Part("MEM_ID") memId: Int,
        @Part("MEM_NAME") memName: String,
        @Part("MEM_TEL") memTel: String,
        @Part("MEM_PASS") memPass: String,
        @Part("MEM_EMAIL") memEmail: String
    ): Call<SignUpModel>
     */

    /**
    직원 정보를 수정하는 api
    @params SFF_ID : get api를 통해 받아온 id값
    @params SFF_NM : 사용자가 입력한 name
    @params SFF_TEL : 사용자가 입력한 tel
    @params SFF_USER_ID : 사용자가 입력한 id
    @params SFF_PASS : 사용자가 입력한 pw
    @return 단순 응답 바디를 반환값으로 받는 Call
     **/

    @FormUrlEncoded
    @PATCH("api/employee/ModifyEmployeeInfo")
    fun getModificationData(
        @Field("SFF_ID") sffId: Int,
        @Field("SFF_NM") sffNm: String,
        @Field("SRS_TEL") srsTel: String,
        @Field("SFF_USR_ID") sffUserId: String,
        @Field("SFF_PASS") sffPass: String
    ): Call<ModificationModel>
}

