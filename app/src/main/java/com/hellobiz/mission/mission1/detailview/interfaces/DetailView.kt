package com.hellobiz.mission.mission1.detailview.interfaces

import com.hellobiz.mission.mission1.detailview.model.MyDetailModel
import com.hellobiz.mission.error.model.ErrorRespose

interface DetailView {
    fun detailSuccess(myDetailModel : MyDetailModel)
    fun detailError(errorResponse:ErrorRespose)
    fun detailFailure(message : Throwable?)
}