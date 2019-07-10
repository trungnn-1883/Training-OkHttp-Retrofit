package com.example.training_okhttp_retrofit.api.model

import com.squareup.moshi.Json

data class Note(

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "content")
    val content: String

)
