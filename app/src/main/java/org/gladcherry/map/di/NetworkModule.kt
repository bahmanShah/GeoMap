package org.gladcherry.map.di

import okhttp3.OkHttpClient
import org.gladcherry.map.data.network.ApiService
import org.gladcherry.map.data.network.RequestInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())

        .baseUrl("https://map.ir/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}
