package com.example.training_okhttp_retrofit.api.model

import com.example.training_okhttp_retrofit.api.callback.StatusCode
import okhttp3.ResponseBody

open class BaseException(
    val errorBody: ResponseBody?,
    message: String?,
    cause: Throwable?,
    val statusCode: StatusCode
) : Exception(message, cause)