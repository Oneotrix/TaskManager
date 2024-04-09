package com.dirion.walltechtodo.di.app.modules.dependency

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.dirion.walltechtodo.data.datasource.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDependencyModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("WalltechPref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context) : AppDatabase {
        return synchronized(AppDatabase::class) {
            Room.databaseBuilder(
                context = context,
                AppDatabase::class.java,
                "walltechtodo")
                .build()
        }
    }
}