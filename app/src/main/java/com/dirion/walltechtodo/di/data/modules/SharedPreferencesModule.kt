package com.dirion.walltechtodo.di.data.modules

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("WalltechPref", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideEditor(sharedPreferences: SharedPreferences): Editor {
        return sharedPreferences.edit()
    }

}