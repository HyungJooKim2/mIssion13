package com.hellobiz.mission2.mainview.Interface

import com.hellobiz.mission1.error.model.ErrorRespose
import com.hellobiz.mission2.mainview.model.MainViewModel

interface MainView {
    fun MainViewSuccess(myViewModel: MainViewModel?)
    fun MainViewError(errorResponse: ErrorRespose)
    fun MainViewFailure(message : Throwable?)
}