package com.hellobiz.mission2.mainview.maininterface

import com.hellobiz.mission1.error.model.ErrorRespose
import com.hellobiz.mission2.mainview.model.MainViewModel
import com.hellobiz.mission2.mainview.model.MainViewResponse

interface MainView {
    fun mainViewSuccess(mainViewModel: MainViewModel?)
    fun mainViewError(errorResponse: ErrorRespose)
    fun mainViewFailure(message : Throwable?)
}