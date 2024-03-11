package com.dirion.walltechtodo.di

import com.dirion.walltechtodo.di.data.DataComponent
import com.dirion.walltechtodo.di.domain.DomainComponent
import com.dirion.walltechtodo.di.presentation.PresentationComponent
import com.dirion.walltechtodo.di.presentation.subcomponents.ActivityComponent
import dagger.Module

@Module(subcomponents = [
    PresentationComponent::class,
    DataComponent::class,
    DomainComponent::class,
    ]
)
class AppSubcomponents {
}