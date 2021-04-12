package com.hellobiz.mission.mission4.puttingtogether.page3.`interface`

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.model.ManagementModel


interface Management {
    fun managementSuccess(managementModel: ManagementModel?)
    fun managementError(errorResponse: ErrorRespose)
    fun managementFailure(message : Throwable?)
}