package br.com.fornaro.domain.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


@Suppress("DEPRECATION")
@SuppressLint("MissingPermission")
fun Context.isNetworkConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
            return when {
                it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                        && it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        && (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) -> true
                else -> false
            }
        }
    } else {
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo?.isConnected ?: false
    }
    return false
}