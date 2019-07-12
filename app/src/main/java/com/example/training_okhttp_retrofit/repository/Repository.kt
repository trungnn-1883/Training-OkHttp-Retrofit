package com.example.training_okhttp_retrofit.repository

import androidx.lifecycle.LiveData
import com.example.training_okhttp_retrofit.api.callback.DataWrapper
import com.example.training_okhttp_retrofit.datasource.DataSource
import com.example.training_okhttp_retrofit.local.LocalData
import com.example.training_okhttp_retrofit.remote.RemoteData
import com.example.training_okhttp_retrofit.util.NetWorkUtil

object Repository {

    const val PAGE = "page"
    const val ID = "id"
    const val USER_ID = "userId"
    const val POST_ID = "postId"

    inline fun <reified Entity : Any> of(): Repo<Entity> {
        return Repo<Entity>(RemoteData.of(Entity::class), LocalData.of(Entity::class))
    }

}


class Repo<T>(val api: DataSource<T>, val db: DataSource<T>) : DataSource<T> {

    override fun getAll(): LiveData<DataWrapper<T>>? {
//        if (NetWorkUtil.isNetworkAvailable()) {
            return api.getAll()
//        } else {
//            return db.getAll()
//        }
    }

}