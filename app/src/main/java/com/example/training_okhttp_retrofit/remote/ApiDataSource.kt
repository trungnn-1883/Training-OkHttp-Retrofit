package com.example.training_okhttp_retrofit.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.training_okhttp_retrofit.api.callback.ApiErrorHandler
import com.example.training_okhttp_retrofit.api.callback.DataWrapper
import com.example.training_okhttp_retrofit.api.callback.GenericRequestHandler
import com.example.training_okhttp_retrofit.api.callback.MockioError
import com.example.training_okhttp_retrofit.api.client.APIClient
import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import com.example.training_okhttp_retrofit.datasource.DataSource
import retrofit2.Call

class ApiDataSource : DataSource<ListNoteReponse>, GenericRequestHandler<ListNoteReponse>() {

    val mNoteService = APIClient.createNoteService()

    override val errorHandler: ApiErrorHandler = MockioError()

    override fun makeRequest(): Call<ListNoteReponse>? {
        return mNoteService?.getAllNote()
    }

    override fun getAll(): LiveData<DataWrapper<ListNoteReponse>>? {
        Log.d("Toast", "BBBBBBBBBBBBBBBBBBBB")
        return doRequest()
    }

    companion object {

        fun createInstance(): ApiDataSource {
            val apiDataSource = ApiDataSource()
            return apiDataSource
        }
    }
}