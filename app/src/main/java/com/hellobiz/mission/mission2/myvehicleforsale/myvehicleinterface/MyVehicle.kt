package com.hellobiz.mission.mission2.myvehicleforsale.myvehicleinterface

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission2.mainview.model.MainViewModel

interface MyVehicle {
    fun myVehicleSuccess(myVehicleModel: MainViewModel?)
    fun myVehicleError(errorResponse: ErrorRespose)
    fun myVehicleFailure(message : Throwable?)
}