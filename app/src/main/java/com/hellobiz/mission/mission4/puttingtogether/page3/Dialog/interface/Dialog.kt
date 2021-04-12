package com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.`interface`

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.Dialog.model.DialogModel

interface Dialog {
    fun dialogSuccess(dialogModel: DialogModel?)
    fun dialogError(errorResponse: ErrorRespose)
    fun dialogFailure(message : Throwable?)
}