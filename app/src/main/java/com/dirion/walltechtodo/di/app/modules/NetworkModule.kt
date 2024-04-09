package com.dirion.walltechtodo.di.app.modules

import com.dirion.walltechtodo.di.app.modules.dependency.NetworkDependencyModule
import dagger.Module

@Module(includes = [NetworkDependencyModule::class])
class NetworkModule