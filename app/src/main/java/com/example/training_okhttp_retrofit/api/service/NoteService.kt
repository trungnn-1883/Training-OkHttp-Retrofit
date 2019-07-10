package com.example.training_okhttp_retrofit.api.service

import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import retrofit2.http.GET
import retrofit2.Call

interface NoteService {

    @GET("/getAllNote")
    fun getAllNote(): Call<ListNoteReponse>

}