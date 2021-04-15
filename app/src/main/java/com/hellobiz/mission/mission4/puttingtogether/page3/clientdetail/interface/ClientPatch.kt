package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.`interface`

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.model.ClientPatchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupPatchModel

interface ClientPatch {
    fun clientPatchSuccess(clientPatchModel: ClientPatchModel?)
    fun clientPatchError(errorResponse: ErrorRespose)
    fun clientPatchFailure(message : Throwable?)
}