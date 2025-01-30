package cleancode.di

import android.content.Context
import cleancode.net.RestApi
import cleancode.net.RestApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModuleRemote {
    @Provides
    @Singleton
    fun provideApiRest(
        @ApplicationContext app: Context,
    ): RestApi = RestApiImpl(app)
}
