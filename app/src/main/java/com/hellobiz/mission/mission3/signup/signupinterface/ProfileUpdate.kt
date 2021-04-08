package com.hellobiz.mission.mission3.signup.signupinterface

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission3.signup.model.ProfileUpdateModel

interface ProfileUpdate {
    fun profileUpdateSuccess(profileUpdateModel: ProfileUpdateModel?)
    fun profileUpdateError(errorResponse: ErrorRespose)
    fun profileUpdateFailure(message : Throwable?)
}