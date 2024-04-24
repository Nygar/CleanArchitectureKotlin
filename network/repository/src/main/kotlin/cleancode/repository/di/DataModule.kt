package cleancode.repository.di

import android.content.Context
import cleancode.database.AppDatabase
import cleancode.database.api.CategoryCache
import cleancode.database.api.MessageCache
import cleancode.database.api.UserCache
import cleancode.database.api.UserLoggedCache
import cleancode.database.impl.CategoryCacheImpl
import cleancode.database.impl.MessageCacheImpl
import cleancode.database.impl.UserCacheImpl
import cleancode.database.impl.UserLoggedCacheImpl
import cleancode.net.RestApi
import cleancode.repository.DataRepository
import cleancode.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {
    @Provides
    fun provideDataRepository(
        api: RestApi,
        categoryCache: CategoryCache,
        messageCache: MessageCache,
        userCache: UserCache,
        userLoggedCache: UserLoggedCache,
    ): DataRepository = DataRepositoryImpl(api, categoryCache, messageCache, userCache, userLoggedCache)

    // Database
    @Provides
    fun provideCategoryCache(
        @ApplicationContext app: Context,
        database: AppDatabase,
    ): CategoryCache = CategoryCacheImpl(app, database)

    @Provides
    fun provideMessageCache(
        @ApplicationContext app: Context,
        database: AppDatabase,
    ): MessageCache = MessageCacheImpl(app, database)

    @Provides
    fun provideUserCache(
        @ApplicationContext app: Context,
        database: AppDatabase,
    ): UserCache = UserCacheImpl(app, database)

    @Provides
    fun provideUserLoggedCache(
        @ApplicationContext app: Context,
        database: AppDatabase,
    ): UserLoggedCache = UserLoggedCacheImpl(app, database)
}
