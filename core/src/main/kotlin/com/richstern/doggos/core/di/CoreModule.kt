package com.richstern.doggos.core.di

import com.richstern.doggos.core.network.DoggosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val DOGGOS_BASE_URL = "https://dog.ceo/api/"

@Module
@InstallIn(ApplicationComponent::class)
class CoreModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Provides
    fun provideRetrofitService(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(DOGGOS_BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideDoggosRetrofitService(
        retrofit: Retrofit
    ): DoggosService =
        retrofit.create(DoggosService::class.java)
}