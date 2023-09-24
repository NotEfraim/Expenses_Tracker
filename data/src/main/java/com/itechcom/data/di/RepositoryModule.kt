package com.itechcom.data.di

import com.itechcom.data.repository.FacebookLoginRepository
import com.itechcom.data.repository.FirebaseLoginRepository
import com.itechcom.data.repository.GoogleLoginRepository
import com.itechcom.data.repository.SharedPrefRepository
import com.itechcom.data.repositoryImp.FacebookLoginRepositoryImp
import com.itechcom.data.repositoryImp.FirebaseLoginRepositoryImp
import com.itechcom.data.repositoryImp.GoogleLoginRepositoryImp
import com.itechcom.data.repositoryImp.SharePrefRepositoryImp
import com.itechcom.data.storage.firebase.auth.GoogleAuthClient
import com.itechcom.data.storage.firebase.database.FirebaseDatabaseManager
import com.itechcom.data.storage.sharedpref.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGoogleRepository(googleAuthClient: GoogleAuthClient) : GoogleLoginRepository{
        return GoogleLoginRepositoryImp(googleAuthClient)
    }

    @Provides
    @Singleton
    fun providesFirebaseRepository(fdm: FirebaseDatabaseManager) : FirebaseLoginRepository{
        return FirebaseLoginRepositoryImp(fdm)
    }

    @Provides
    @Singleton
    fun providesFacebookRepository() : FacebookLoginRepository{
        return FacebookLoginRepositoryImp()
    }

    @Provides
    @Singleton
    fun provideSharedPrefRepository(sharedPrefManager: SharedPrefManager) : SharedPrefRepository{
        return SharePrefRepositoryImp(sharedPrefManager)
    }
}