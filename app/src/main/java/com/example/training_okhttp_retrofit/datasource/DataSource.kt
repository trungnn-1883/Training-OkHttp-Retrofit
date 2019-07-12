package com.example.training_okhttp_retrofit.datasource

import androidx.lifecycle.LiveData
import com.example.training_okhttp_retrofit.api.callback.DataWrapper
import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import retrofit2.Call

interface DataSource<T> {

    fun getAll(): LiveData<DataWrapper<T>>?

}