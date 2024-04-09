package com.dirion.walltechtodo.di.app.modules

import com.dirion.walltechtodo.di.app.modules.dependency.LocalDependencyModule
import dagger.Module

@Module(includes = [LocalDependencyModule::class])
class LocalModule