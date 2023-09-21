package com.itechcom.expensestracker.di

import android.content.Context
import com.itechcom.expensestracker.data.room.RoomDBManager
import com.itechcom.expensestracker.data.sharedpref.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ComponentsModule {

    @Provides
    @Singleton
    fun provideRoomDBManager(@ApplicationContext context: Context) = RoomDBManager(context)

    @Provides
    @Singleton
    fun providesSharedPref(@ApplicationContext context: Context) = SharedPrefManager(context)
}