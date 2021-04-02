package com.hellobiz.mission1.detailview.interfaces

import com.hellobiz.mission1.detailview.model.MyDetailModel
import com.hellobiz.mission1.error.model.ErrorRespose

interface DetailView {
    fun DetailSuccess(myDetailModel : MyDetailModel)
    fun DetailError(errorResponse:ErrorRespose)
    fun DetailFailure(message : Throwable?)
}