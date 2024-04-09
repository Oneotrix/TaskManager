package com.dirion.walltechtodo.di.app.modules.dependency

import android.util.Log
import com.dirion.walltechtodo.data.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkDependencyModule {

    @Provides
    fun walltechtodoService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun retrofitService(
        convertedFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(convertedFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun jsonConvertedFactory(json: Json, contentType: MediaType): Converter.Factory {
        return json.asConverterFactory(contentType)
    }

    @Provides
    fun json(): Json {
        return Json
    }

    @Provides
    fun contentType(): MediaType {
        return "application/json".toMediaType()
    }

    @Provides
    @Singleton
    fun okHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}