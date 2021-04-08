package com.hellobiz.mission.mission3.signup.signupinterface

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission3.signup.model.ModificationModel

interface EmployeeModification {
    fun modificationSuccess(modificationModel: ModificationModel?)
    fun modificationError(errorResponse: ErrorRespose)
    fun modificationFailure(message : Throwable?)
}