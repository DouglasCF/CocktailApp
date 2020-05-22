package br.com.fornaro.domain.api

import br.com.fornaro.domain.api.response.CategoriesResponse
import retrofit2.http.GET

interface CategoryApi {

    @GET("list.php?c=list")
    suspend fun getCategories(): CategoriesResponse
}