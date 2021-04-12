package com.hellobiz.mission.mission4.puttingtogether.mission3.Dialog.`interface`

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.mission3.Dialog.model.DialogModel
import com.hellobiz.mission.mission4.puttingtogether.mission3.model.ManagementModel

interface Dialog {
    fun dialogSuccess(dialogModel: DialogModel?)
    fun dialogError(errorResponse: ErrorRespose)
    fun dialogFailure(message : Throwable?)
}