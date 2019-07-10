package com.example.training_okhttp_retrofit.api.callback

import okhttp3.RequestBody
import androidx.lifecycle.LiveData
import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import retrofit2.Call
import retrofit2.Response


class GetDataInteractor : GenericRequestHandler<ListNoteReponse>() {

    private val authService = APIService.getInstance().getServiceForApi(AuthService::class.java)
    private var userId: String? = null
    private var pinCode: String? = null

    fun onAuthRequest(): LiveData<DataWrapper<ListNoteReponse>> {
        return doRequests()
    }

    override fun makeRequest(): Call<Response<ListNoteReponse>> {
        return authService.postUserPhoneLogin(RequestBody.UserPhoneLogin(userId, pinCode))
    }

    companion object {

        fun createInstance(userId: String, pinCode: String): GetDataInteractor {
            val signInWithPinLoader = GetDataInteractor()
            signInWithPinLoader.userId = userId
            signInWithPinLoader.pinCode = pinCode
            return signInWithPinLoader
        }
    }
}