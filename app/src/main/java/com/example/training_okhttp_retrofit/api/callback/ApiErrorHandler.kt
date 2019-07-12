package com.example.training_okhttp_retrofit.api.callback

import com.example.training_okhttp_retrofit.api.model.BaseException
import retrofit2.Response

/*
*ApiErrorHandler class should be extended to handle custom exceptions
*
**/
open class ApiErrorHandler {


    /* Method should be overridden to return custom exception type which
    *  would be a sub-type of Exception or to have the response body,
    *  return a sub-type of kexception()
    */
    open fun getExceptionType(response: Response<*>): Exception {
        return BaseException(response.errorBody(), response.message(), null, StatusCode(response.code()))
    }


}