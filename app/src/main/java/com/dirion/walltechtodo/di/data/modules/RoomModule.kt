package com.dirion.walltechtodo.di.data.modules

import android.content.Context
import androidx.room.Room
import com.dirion.walltechtodo.data.datasource.local.room.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {
    @Provides
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