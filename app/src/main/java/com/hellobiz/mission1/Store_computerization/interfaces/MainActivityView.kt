package com.hellobiz.mission1.Store_computerization.interfaces

import com.hellobiz.mission1.Store_computerization.error.model.ErrorRespose
import com.hellobiz.mission1.Store_computerization.model.MyStoreModel


interface MainActivityView {
    fun MainSuccess(MyStoreModel : MyStoreModel)
    fun MainError(errorResponse:ErrorRespose)
    fun MainFailure(message : Throwable?)
}