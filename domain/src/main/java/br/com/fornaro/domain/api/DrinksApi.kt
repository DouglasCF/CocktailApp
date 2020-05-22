package br.com.fornaro.domain.api

import br.com.fornaro.domain.api.response.DrinksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksApi {

    @GET("filter.php")
    suspend fun getDrinks(@Query("c") categoryName: String): DrinksResponse
}