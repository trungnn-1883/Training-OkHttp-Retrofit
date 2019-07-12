package com.example.training_okhttp_retrofit.api.callback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call


abstract class GenericRequestHandler<R> {

    /*
Override errorHandler() to return custom ApiErrorHandler object
where error/exception creation mapping resides.
 */
    open val errorHandler: ApiErrorHandler = ApiErrorHandler()

    protected abstract fun makeRequest(): Call<R>?

    open fun doRequest(): LiveData<DataWrapper<R>> {
        val liveData = MutableLiveData<DataWrapper<R>>()
        makeRequest()?.enqueue(object : BaseApiCallback<R>(errorHandler) {
            override fun handleResponseData(data: R?, statusCode: StatusCode) {
                liveData.value = DataWrapper(data = data, statusCode = statusCode)
            }

            override fun handleException(ex: Exception, statusCode: StatusCode) {
                liveData.value = DataWrapper(apiException = ex, statusCode = statusCode)
            }
        })
        return liveData
    }
}