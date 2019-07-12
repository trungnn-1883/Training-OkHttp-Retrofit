package com.example.training_okhttp_retrofit.api.callback

import android.util.Log
import androidx.lifecycle.Observer

abstract class ApiObserver<T>(private val exceptionHandler: ((Exception) -> Unit)? = null) : Observer<DataWrapper<T>> {

    override fun onChanged(wrapper: DataWrapper<T>?) {
        wrapper?.let {
            finish(it.statusCode)
            it.apiException?.let { ex ->
                handleErrorInstance(ex)
                Log.d("API", "error")
            }
            it.data?.let { data ->
                onSuccess(data)
                Log.d("API", "success")
            }
            return
        }
        handleErrorInstance(Exception())
        Log.d("API", "handleErrorInstance")

    }

    private fun handleErrorInstance(ex: Exception) {
        exceptionHandler?.let { func ->
            func.invoke(ex)
            return
        }
        onException(ex)
    }

    /**
     * Method to be called on successful load of data. This method is guaranteed to contain the returned data
     *
     * @param data
     */
    abstract fun onSuccess(data: T)

    /**
     * Method to handle exception that occurs during request processing.
     * @param exception
     */
    open fun onException(exception: Exception) {

    }

    /**
     * Triggered for any request by user, both successful and unsuccessful
     * takes in a StatusCode, so you can determine the state of the requests
     */

    open fun finish(statusCode: StatusCode?) {
        //Check for 20x and perform operation
    }
}