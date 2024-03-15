package com.dirion.walltechtodo.di.data

import com.dirion.walltechtodo.data.ApiService
import com.dirion.walltechtodo.di.data.modules.ModuleRetrofit
import com.dirion.walltechtodo.di.data.modules.OkHttpModule
import com.dirion.walltechtodo.di.scope.ScopeData
import dagger.Subcomponent
import okhttp3.OkHttpClient

@Subcomponent(
    modules = [
        ModuleRetrofit::class,
        OkHttpModule::class,
    ]
)
@ScopeData
interface DataComponent {

    fun walltechtodoService() : ApiService

    fun clientOkHttp(): OkHttpClient

    @Subcomponent.Builder
    interface Builder {
        fun build() : DataComponent

    }

}