package com.example.training_okhttp_retrofit.local

import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import com.example.training_okhttp_retrofit.datasource.DataSource
import com.example.training_okhttp_retrofit.remote.ApiDataSource
import kotlin.reflect.KClass

object LocalData {

    fun <Entity : Any> of(clazz: KClass<*>): DataSource<Entity> {
        return when (clazz) {
            ListNoteReponse::class -> LocalDataSource()
            else -> throw IllegalArgumentException("Unsupported data type")
        } as DataSource<Entity>
    }
}