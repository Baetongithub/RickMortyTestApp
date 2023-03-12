package com.example.rickmortytestapp.presentation.utils.network_helper
import android.content.Context
import com.example.rickmortytestapp.presentation.utils.network_helper.NetworkUtils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable(context)) {
            throw NoConnectionException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    inner class NoConnectionException : IOException() {
        override val message: String
            get() = super.message ?: "No Internet Connection"
    }
}