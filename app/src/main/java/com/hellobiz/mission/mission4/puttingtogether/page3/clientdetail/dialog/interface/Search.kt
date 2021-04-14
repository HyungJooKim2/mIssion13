package com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.`interface`

import com.hellobiz.mission.error.model.ErrorRespose
import com.hellobiz.mission.mission4.puttingtogether.page3.clientdetail.dialog.model.SearchModel
import com.hellobiz.mission.mission4.puttingtogether.page3.dialog.model.ClientModel

interface Search {
    fun searchSuccess(searchModel: SearchModel?)
    fun searchError(errorResponse: ErrorRespose)
    fun searchFailure(message : Throwable?)
}