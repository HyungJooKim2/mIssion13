package com.hellobiz.mission.mission4.puttingtogether.page3.`interface`

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.model.GroupModel


interface GroupInterface {
    fun myGroupSuccess(groupModel: GroupModel?)
    fun myGroupError(errorResponse: ErrorRespose)
    fun myGroupFailure(message : Throwable?)
}