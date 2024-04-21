package cleancode.di

import cleancode.repository.DataRepository
import cleancode.usecase.CategoryUsecase
import cleancode.usecase.CategoryUsecaseImpl
import cleancode.usecase.MessageUsecase
import cleancode.usecase.MessageUsecaseImpl
import cleancode.usecase.UserLoggedUsecase
import cleancode.usecase.UserLoggedUsecaseImpl
import cleancode.usecase.UserUsecase
import cleancode.usecase.UserUsecaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {


    //Database
    @Provides
    fun provideCategoryUsecase(
        repository: DataRepository
    ): CategoryUsecase = CategoryUsecaseImpl(repository)

    @Provides
    fun provideMessageUsecase(
        repository: DataRepository
    ): MessageUsecase = MessageUsecaseImpl(repository)

    @Provides
    fun provideUserUsecase(
        repository: DataRepository
    ): UserUsecase = UserUsecaseImpl(repository)

    @Provides
    fun provideUserLoggedUsecase(
        repository: DataRepository
    ): UserLoggedUsecase = UserLoggedUsecaseImpl(repository)
}