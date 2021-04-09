package com.hellobiz.mission.store_computerization.interfaces

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.store_computerization.model.MyStoreModel


interface MainActivityView {
    fun mainSuccess(myStoreModel : MyStoreModel)
    fun mainError(errorResponse:ErrorRespose)
    fun mainFailure(message : Throwable?)
}