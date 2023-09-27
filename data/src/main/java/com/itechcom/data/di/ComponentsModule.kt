package com.itechcom.data.di

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.itechcom.data.storage.firebase.auth.BasicAuthClient
import com.itechcom.data.storage.firebase.auth.FacebookAuthClient
import com.itechcom.data.storage.firebase.auth.GoogleAuthClient
import com.itechcom.data.storage.firebase.auth.LoginUtil
import com.itechcom.data.storage.firebase.database.FirebaseDatabaseManager
import com.itechcom.data.storage.room.RoomDBManager
import com.itechcom.data.storage.sharedpref.SharedPrefManager
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

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context,
        signInClient: SignInClient
    ) = GoogleAuthClient(
        context = context,
        oneTapSignClient = signInClient)

    @Provides
    @Singleton
    fun provideOneTapClient(@ApplicationContext context: Context) = Identity.getSignInClient(context)

    @Provides
    @Singleton
    fun providesFirebaseDatabaseManager() = FirebaseDatabaseManager()

    @Provides
    @Singleton
    fun providesBaseAuthClient() = BasicAuthClient()

    @Provides
    @Singleton
    fun provideFragmentManager(activity : FragmentActivity) = activity.supportFragmentManager

    @Provides
    @Singleton
    fun provideLoginUtil(signInClient : SignInClient) = LoginUtil(signInClient)

    @Provides
    @Singleton
    fun provideFacebookAuthClient() = FacebookAuthClient()

}