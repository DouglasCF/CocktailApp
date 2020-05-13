package br.com.fornaro.domain.api

import br.com.fornaro.domain.models.response.DrinkDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkDetailApi {

    @GET("lookup.php")
    suspend fun getDrink(@Query("i") drinkId: String): DrinkDetailResponse
}