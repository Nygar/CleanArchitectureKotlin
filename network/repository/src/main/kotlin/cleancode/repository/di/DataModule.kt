package cleancode.repository.di

import cleancode.database.api.CategoryCache
import cleancode.database.api.MessageCache
import cleancode.database.api.UserCache
import cleancode.database.api.UserLoggedCache
import cleancode.net.RestApi
import cleancode.repository.DataRepository
import cleancode.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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
}
