package com.example.training_okhttp_retrofit.api.client

import com.example.training_okhttp_retrofit.api.service.NoteService
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import android.content.Context
import com.example.training_okhttp_retrofit.R
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory


class APIClient {

    @Throws(
        CertificateException::class,
        IOException::class,
        KeyStoreException::class,
        NoSuchAlgorithmException::class,
        KeyManagementException::class
    )
     fun getSSLConfig(context: Context): SSLContext {

        // Loading CAs from an InputStream
        var cf: CertificateFactory? = null
        cf = CertificateFactory.getInstance("X.509")

        var ca: Certificate? = null
        // I'm using Java7. If you used Java6 close it manually with finally.

        context.getResources().openRawResource(R.raw.ssl)?.let {
            ca = cf.generateCertificate(it)
        }

        // Creating a KeyStore containing our trusted CAs
        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", ca)

        // Creating a TrustManager that trusts the CAs in our KeyStore.
        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
        tmf.init(keyStore)

        // Creating an SSLSocketFactory that uses our TrustManager
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, tmf.getTrustManagers(), null)

        return sslContext
    }


    companion object {
        private const val BASE_URL = "http://demo7904624.mockable.io/"

        private var mInstance: Retrofit? = null

        private fun getAPIService(): Retrofit? {
            if (mInstance == null) {
                var authToken: String
                val clientBuilder = OkHttpClient.Builder()/*.cache(Cache)*/
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

//                clientBuilder.sslSocketFactory(get)
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

//    fun getAllNote(): Call<ListNoteReponse>? {
//        return getAPIService()?.create(NoteService::class.java)?.getAllNote()
//    }

        fun createNoteService(): NoteService? {
            return getAPIService()?.create(NoteService::class.java)
        }

    }
}