package com.dirion.walltechtodo.di.data

import com.dirion.walltechtodo.data.ApiServiceWalltechtodo
import com.dirion.walltechtodo.data.TasksRepositoryImpl
import com.dirion.walltechtodo.di.data.modules.ModuleRetrofit
import com.dirion.walltechtodo.di.data.modules.OkHttpModule
import com.dirion.walltechtodo.di.data.modules.RepositoryModule
import com.dirion.walltechtodo.di.scope.ScopeApplication
import com.dirion.walltechtodo.di.scope.ScopeData
import com.dirion.walltechtodo.domain.repository.TasksRepository
import dagger.Component
import dagger.Provides
import dagger.Subcomponent
import okhttp3.OkHttpClient

@Subcomponent(
    modules = [
        ModuleRetrofit::class,
        OkHttpModule::class,
        RepositoryModule::class,
    ]
)
@ScopeData
interface DataComponent {

    fun walltechtodoService() : ApiServiceWalltechtodo

    fun clientOkHttp(): OkHttpClient

    @Subcomponent.Builder
    interface Builder {
        fun build() : DataComponent

    }

}