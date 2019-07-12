package com.example.training_okhttp_retrofit.remote

import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import com.example.training_okhttp_retrofit.datasource.DataSource
import kotlin.reflect.KClass

object RemoteData {

    fun <Entity : Any> of(clazz: KClass<*>): DataSource<Entity> {
        return when (clazz) {
            ListNoteReponse::class -> ApiDataSource()
            else -> throw IllegalArgumentException("Unsupported data type")
        } as DataSource<Entity>
    }
}