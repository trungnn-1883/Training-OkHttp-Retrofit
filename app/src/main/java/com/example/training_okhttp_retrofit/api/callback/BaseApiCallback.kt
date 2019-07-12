package com.example.training_okhttp_retrofit.api.callback

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class BaseApiCallback<T>(private val errorHandler: ApiErrorHandler) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        val statusCode = StatusCode(response.code())
        when (response.code()) {
            in 200 until 209 ->
                when (response.body() != null) {
                    true -> handleResponseData(response.body(), statusCode)
                }
            else -> handleError(response, statusCode)
        }
    }

    abstract protected fun handleResponseData(data: T?, statusCode: StatusCode)

    protected fun handleError(response: Response<T>, statusCode: StatusCode) {
        handleException(errorHandler.getExceptionType(response), statusCode)
        Log.d("Code", " " + response.code()  )
    }

    abstract protected fun handleException(t: Exception, statusCode: StatusCode)

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t is java.lang.Exception) {
            handleException(t, StatusCode(-1))
        } else {
            Log.d("onFailure", "Error")
        }
    }
}