package com.hellobiz.mission.store_computerization.interfaces

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.store_computerization.model.MyStoreModel


interface MainActivityView {
    fun MainSuccess(MyStoreModel : MyStoreModel)
    fun MainError(errorResponse:ErrorRespose)
    fun MainFailure(message : Throwable?)
}