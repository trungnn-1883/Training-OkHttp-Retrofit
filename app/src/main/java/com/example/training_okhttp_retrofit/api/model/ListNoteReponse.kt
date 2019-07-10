package com.example.training_okhttp_retrofit.api.model

import com.squareup.moshi.Json

data class ListNoteReponse(

    @field:Json(name = "list")
    val listNote: List<Note>

)