package com.hellobiz.mission.mission3.signup.signupinterface

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission3.signup.model.SignUpModel

interface SignUp {
    fun signUpSuccess(signUpModel: SignUpModel?)
    fun signUpError(errorResponse: ErrorRespose)
    fun signUpFailure(message : Throwable?)
}