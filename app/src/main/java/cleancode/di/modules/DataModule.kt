package cleancode.di.modules

import android.content.Context
import cleancode.database.AppDatabase
import cleancode.database.api.CategoryCache
import cleancode.database.api.MessageCache
import cleancode.database.api.UserCache
import cleancode.database.api.UserLoggedCache
import cleancode.database.impl.CategoryCacheImpl
import cleancode.database.impl.MessageCacheImpl
import cleancode.database.impl.UserCacheImpl
import cleancode.database.impl.UserLoggedCaheImpl
import cleancode.net.RestApi
import cleancode.repository.DataRepository
import cleancode.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataRepository(@ApplicationContext app: Context,
                              api: RestApi,
                              categoryCache: CategoryCache,
                              messageCache: MessageCache,
                              userCache: UserCache,
                              userLoggedCache: UserLoggedCache
    ): DataRepository =
        DataRepositoryImpl(app, api, categoryCache, messageCache, userCache, userLoggedCache)

    //Database
    @Provides
    @Singleton
    fun provideCategoryCache(@ApplicationContext app: Context,
                             database: AppDatabase
    ): CategoryCache = CategoryCacheImpl(app, database)

    @Provides
    @Singleton
    fun provideMessageCache(@ApplicationContext  app: Context,
                            database: AppDatabase
    ): MessageCache = MessageCacheImpl(app, database)

    @Provides
    @Singleton
    fun provideuserCache(@ApplicationContext  app: Context,
                         database: AppDatabase
    ): UserCache = UserCacheImpl(app, database)

    @Provides
    @Singleton
    fun provideUserLoggedCache(@ApplicationContext  app: Context,
                               database: AppDatabase
    ): UserLoggedCache = UserLoggedCaheImpl(app, database)
}