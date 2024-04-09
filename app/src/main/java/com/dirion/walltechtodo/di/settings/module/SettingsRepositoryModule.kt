package com.dirion.walltechtodo.di.settings.module

import com.dirion.walltechtodo.data.SettingsRepository
import com.dirion.walltechtodo.data.datasource.local.shared_prefs.SharedPrefsHelper
import com.dirion.walltechtodo.di.settings.SettingsScope
import com.dirion.walltechtodo.domain.repository.ISettingsRepository
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json

@Module
class SettingsRepositoryModule {

    @Provides
    @SettingsScope
    fun provideSettingsRepository(
        sharedPrefsHelper: SharedPrefsHelper,
        json: Json
    ) : ISettingsRepository {
        return SettingsRepository(sharedPrefsHelper, json)
    }
}