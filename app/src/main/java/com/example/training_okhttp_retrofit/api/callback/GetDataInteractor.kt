package com.example.training_okhttp_retrofit.api.callback

import com.example.training_okhttp_retrofit.api.client.APIClient
import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import retrofit2.Call


class NoteInteractor : GenericRequestHandler<ListNoteReponse>() {

    private val authService = APIClient.createNoteService()

    override val errorHandler: ApiErrorHandler = MockioError()


    override fun makeRequest(): Call<ListNoteReponse>? {
        return authService?.getAllNote()
    }

    companion object {

        fun createInstance(): NoteInteractor {
            val noteInteractor = NoteInteractor()
            return noteInteractor
        }
    }
}