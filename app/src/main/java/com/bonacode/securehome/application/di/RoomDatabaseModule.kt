package com.bonacode.securehome.application.di

import android.content.Context
import androidx.room.Room
import com.bonacode.securehome.application.config.DATABASE_NAME
import com.bonacode.securehome.data.database.MyRoomDatabase
import com.bonacode.securehome.data.feature.actionhistory.dao.ActionHistoryDao
import com.bonacode.securehome.data.feature.favouriteaction.dao.FavouriteActionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomDatabaseModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyRoomDatabase =
        Room.databaseBuilder(context, MyRoomDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @JvmStatic
    @Singleton
    @Provides
    fun provideActionHistoryDao(database: MyRoomDatabase): ActionHistoryDao =
        database.actionHistoryDao()

    @JvmStatic
    @Singleton
    @Provides
    fun provideFavouriteActionDao(database: MyRoomDatabase): FavouriteActionDao =
        database.favouriteActionDao()
}
