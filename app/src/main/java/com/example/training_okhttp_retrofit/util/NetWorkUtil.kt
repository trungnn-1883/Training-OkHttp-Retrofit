package com.example.training_okhttp_retrofit.util

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

 class NetWorkUtil {

    companion object {

        fun isNetworkAvailable(mContext: Any): Boolean {
            val cm =
                (mContext as AppCompatActivity).getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo?.isConnected ?: false
        }
    }

}