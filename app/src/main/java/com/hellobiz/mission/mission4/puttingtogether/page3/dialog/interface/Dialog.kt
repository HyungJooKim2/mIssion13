package com.hellobiz.mission.mission4.puttingtogether.page3.dialog.`interface`

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.DialogModel

interface Dialog {
    fun dialogSuccess(dialogModel: DialogModel?)
    fun dialogError(errorResponse: ErrorRespose)
    fun dialogFailure(message : Throwable?)
}