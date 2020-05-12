package br.com.fornaro.domain.di

import android.content.Context
import br.com.fornaro.domain.BuildConfig
import br.com.fornaro.domain.api.CategoryApi
import br.com.fornaro.domain.api.ConnectivityInterceptor
import br.com.fornaro.domain.api.DrinksApi
import br.com.fornaro.domain.repositories.CategoryRepository
import br.com.fornaro.domain.repositories.DrinksRepository
import br.com.fornaro.domain.usecases.CategoryUseCases
import br.com.fornaro.domain.usecases.DrinksUseCases
import br.com.fornaro.domain.usecases.FavoritesUseCases
import br.com.fornaro.domain.usecases.SearchUseCases
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val useCasesModules = module {
    single { CategoryUseCases(get()) }
    single { SearchUseCases() }
    single { FavoritesUseCases() }
    single { DrinksUseCases(get()) }
}

private val repositoriesModules = module {
    single { CategoryRepository(get()) }
    single { DrinksRepository(get()) }
}

private val netWorkModules = module {
    single { providesOkHttpClient(get()) }
    single { providesRetrofit(get()) }
    single { providesCategoryApi(get()) }
    single { providesDrinksApi(get()) }
}

fun providesOkHttpClient(context: Context): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(ConnectivityInterceptor(context))
    .build()

fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .client(okHttpClient)
    .build()

fun providesCategoryApi(retrofit: Retrofit): CategoryApi = retrofit.create(CategoryApi::class.java)
fun providesDrinksApi(retrofit: Retrofit): DrinksApi = retrofit.create(DrinksApi::class.java)

val domainModules = listOf(useCasesModules, repositoriesModules, netWorkModules)