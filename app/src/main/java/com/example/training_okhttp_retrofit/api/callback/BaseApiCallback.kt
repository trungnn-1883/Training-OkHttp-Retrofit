package com.example.training_okhttp_retrofit.api.callback

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class BaseApiCallback<T> : Callback<Response<T>> {

    override fun onResponse(call: Call<Response<T>>, response: Response<Response<T>>) {
        when (response.body() != null) {
            true -> handleResponseData(response.body())
            else -> handleError(response)
        }
    }

    abstract protected fun handleResponseData(data: Response<T>?)

    abstract protected fun handleError(response: Response<Response<T>>)

    abstract protected fun handleException(t: Exception)

    override fun onFailure(call: Call<Response<T>>, t: Throwable) {
        if (t is java.lang.Exception) {
            handleException(t)
        } else {
            Log.d("onFailure", "Error")
        }
    }
}