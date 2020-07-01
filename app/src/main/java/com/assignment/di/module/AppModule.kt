package com.assignment.di.module

import android.app.Application
import android.content.Context
import com.assignment.network.Api
import com.assignment.network.ApiHelper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
open class AppModule {

    @Singleton
    @Provides
    open fun provideApi() = Retrofit.Builder()
        .baseUrl("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/")
        .client(ApiHelper().getOkHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(ApiHelper().gsonConverter)
        .build().create(Api::class.java)
}