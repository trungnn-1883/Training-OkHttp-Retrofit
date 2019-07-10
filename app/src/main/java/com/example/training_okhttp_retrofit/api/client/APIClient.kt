package com.example.training_okhttp_retrofit.api.client

import android.text.TextUtils
import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import com.example.training_okhttp_retrofit.api.service.NoteService
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class APIClient {

    companion object {
        private const val BASE_URL = "http://demo7904624.mockable.io/"

        private var mInstance: Retrofit? = null

        private fun getAPIService(): Retrofit? {
            if (mInstance == null) {
                var authToken: String
                val clientBuilder = OkHttpClient.Builder()
                val loggingInterceptor = HttpLoggingInterceptor()

//                loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                authToken = Credentials.basic("trung", "@@1122")
                authToken = "1111111111111"

            val headerAuthorizationInterceptor = object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    var request = chain.request()
                    val headers = request.headers().newBuilder().add("Authorization", authToken).build()
                    request = request.newBuilder().headers(headers).build()
                    return chain.proceed(request)
                }
            }

            clientBuilder.addInterceptor(headerAuthorizationInterceptor)
                .addInterceptor(loggingInterceptor)

            mInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        }
        return mInstance
    }

    fun getAllNote(): Call<ListNoteReponse>? {
        return getAPIService()?.create(NoteService::class.java)?.getAllNote()
    }

}
}