package com.example.training_okhttp_retrofit.api.callback

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Response


abstract class GenericRequestHandler<R> {

    protected abstract fun makeRequest(): Call<Response<R>>

    fun doRequest(): LiveData<DataWrapper<R>> {
        val liveData = MutableLiveData<DataWrapper<R>>()
        val dataWrapper = DataWrapper<R>()
        makeRequest().enqueue(object : BaseApiCallback<R>() {
            override fun handleResponseData(data: Response<R>?) {
                dataWrapper.data = data
                liveData.setValue(dataWrapper)
            }

            override fun handleError(response: Response<Response<R>>) {
                dataWrapper.apiException = response.errorBody()
                liveData.setValue(dataWrapper)
            }

            override fun handleException(t: Exception) {
                dataWrapper.apiException = t
                liveData.setValue(dataWrapper)
            }
        })
        return liveData
    }
}