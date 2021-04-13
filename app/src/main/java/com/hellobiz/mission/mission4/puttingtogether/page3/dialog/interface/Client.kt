package com.hellobiz.mission.mission4.puttingtogether.page3.dialog.`interface`

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.ClientModel

interface Client {
    fun clientSuccess(clientModel: ClientModel?)
    fun clientError(errorResponse: ErrorRespose)
    fun clientFailure(message : Throwable?)
}