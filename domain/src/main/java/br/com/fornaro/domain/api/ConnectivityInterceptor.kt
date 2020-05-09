package br.com.fornaro.domain.api

import android.content.Context
import br.com.fornaro.domain.extensions.isNetworkConnected
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = if (context.isNetworkConnected()) {
        chain.proceed(chain.request())
    } else {
        throw NoConnectivityException()
    }
}

class NoConnectivityException : IOException()