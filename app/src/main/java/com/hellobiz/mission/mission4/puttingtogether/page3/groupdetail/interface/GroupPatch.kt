package com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.`interface`

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.groupdetail.model.GroupPatchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupModel

interface GroupPatch {
    fun groupPatchSuccess(groupPatchModel: GroupPatchModel?)
    fun groupPatchError(errorResponse: ErrorRespose)
    fun groupPatchFailure(message : Throwable?)
}