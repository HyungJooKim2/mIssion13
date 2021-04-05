package com.hellobiz.mission2.mainview.maininterface

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission2.mainview.model.MainViewModel

interface MainView {
    fun mainViewSuccess(mainViewModel: MainViewModel?)
    fun mainViewError(errorResponse: ErrorRespose)
    fun mainViewFailure(message : Throwable?)
}