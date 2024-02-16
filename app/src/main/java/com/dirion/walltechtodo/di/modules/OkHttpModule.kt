package com.dirion.walltechtodo.di.modules

import android.util.Log
import com.dirion.walltechtodo.di.scope.ScopeApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class OkHttpModule {
    @ScopeApplication
    @Provides
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