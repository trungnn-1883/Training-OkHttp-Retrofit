package com.example.training_okhttp_retrofit.api.callback

data class DataWrapper<T>(
    var apiException: Exception? = null,
    var data: T? = null
)