package com.example.training_okhttp_retrofit.local

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.training_okhttp_retrofit.api.callback.DataWrapper
import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import com.example.training_okhttp_retrofit.datasource.DataSource
import retrofit2.Call

class LocalDataSource : DataSource<ListNoteReponse> {

    override fun getAll(): LiveData<DataWrapper<ListNoteReponse>>? {
        Log.d("Toast", "AAAAAAAAAAAAAAAAAAAAa")
        return null
    }
}