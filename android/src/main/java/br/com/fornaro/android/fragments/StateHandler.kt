package br.com.fornaro.android.fragments

interface StateHandler {
    fun handleLoading(visible: Boolean)
    fun handleError(error: Throwable?)
}