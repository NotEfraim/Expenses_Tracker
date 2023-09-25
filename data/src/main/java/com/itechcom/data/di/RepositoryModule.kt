package com.itechcom.data.di

import com.itechcom.data.repository.firebase.FacebookAuthRepository
import com.itechcom.data.repository.firebase.GoogleAuthRepository
import com.itechcom.data.repository.SharedPrefRepository
import com.itechcom.data.repository.firebase.BasicAuthRepository
import com.itechcom.data.repositoryImp.firebase.FacebookAuthRepositoryImp
import com.itechcom.data.repositoryImp.firebase.GoogleAuthRepositoryImp
import com.itechcom.data.repositoryImp.SharePrefRepositoryImp
import com.itechcom.data.repositoryImp.firebase.BasicAuthRepositoryImp
import com.itechcom.data.storage.firebase.auth.BasicAuthClient
import com.itechcom.data.storage.firebase.auth.GoogleAuthClient
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
    fun provideGoogleRepository(googleAuthClient: GoogleAuthClient) : GoogleAuthRepository {
        return GoogleAuthRepositoryImp(googleAuthClient)
    }

    @Provides
    @Singleton
    fun providesFacebookRepository() : FacebookAuthRepository {
        return FacebookAuthRepositoryImp()
    }

    @Provides
    @Singleton
    fun provideSharedPrefRepository(sharedPrefManager: SharedPrefManager) : SharedPrefRepository {
        return SharePrefRepositoryImp(sharedPrefManager)
    }

    @Provides
    @Singleton
    fun provideGenericAuthRepository(basicAuthClient: BasicAuthClient) : BasicAuthRepository {
        return BasicAuthRepositoryImp(basicAuthClient)
    }
}