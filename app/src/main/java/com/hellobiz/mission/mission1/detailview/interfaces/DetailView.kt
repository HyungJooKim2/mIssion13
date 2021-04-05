package com.hellobiz.mission.mission1.detailview.interfaces

import com.hellobiz.mission.mission1.detailview.model.MyDetailModel
import com.hellobiz.mission.error.model.ErrorRespose

interface DetailView {
    fun DetailSuccess(myDetailModel : MyDetailModel)
    fun DetailError(errorResponse:ErrorRespose)
    fun DetailFailure(message : Throwable?)
}