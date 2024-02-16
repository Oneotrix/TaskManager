package com.dirion.walltechtodo.di

import com.dirion.walltechtodo.data.ApiServiceWalltechtodo
import com.dirion.walltechtodo.di.modules.ModuleRetrofit
import com.dirion.walltechtodo.di.modules.OkHttpModule
import com.dirion.walltechtodo.di.scope.ScopeApplication
import dagger.Component
import okhttp3.OkHttpClient

@ScopeApplication
@Component(
    modules = [
        ModuleRetrofit::class,
        OkHttpModule::class,
    ]
)
interface AppComponent {

    fun serviceRetrofit(): ApiServiceWalltechtodo

    fun clientOkHttp(): OkHttpClient

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}