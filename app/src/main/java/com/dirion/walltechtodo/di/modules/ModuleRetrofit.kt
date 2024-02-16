package com.dirion.walltechtodo.di.modules

import com.dirion.walltechtodo.data.ApiServiceWalltechtodo
import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@Module
class ModuleRetrofit {

    @ScopeApplication
    @Provides
    fun walltechtodoService(retrofit: Retrofit): ApiServiceWalltechtodo {
        return retrofit.create(ApiServiceWalltechtodo::class.java)
    }

    @ScopeApplication
    @Provides
    fun retrofitService(
        convertedFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiServiceWalltechtodo.BASE_URL)
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

}