package com.nygar.di

import cleancode.repository.DataRepository
import com.nygar.usecase.CategoryUsecase
import com.nygar.usecase.CategoryUsecaseImpl
import com.nygar.usecase.MessageUsecase
import com.nygar.usecase.MessageUsecaseImpl
import com.nygar.usecase.UserLoggedUsecase
import com.nygar.usecase.UserLoggedUsecaseImpl
import com.nygar.usecase.UserUsecase
import com.nygar.usecase.UserUsecaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    // Database
    @Provides
    fun provideCategoryUsecase(repository: DataRepository): CategoryUsecase = CategoryUsecaseImpl(repository)

    @Provides
    fun provideMessageUsecase(repository: DataRepository): MessageUsecase = MessageUsecaseImpl(repository)

    @Provides
    fun provideUserUsecase(repository: DataRepository): UserUsecase = UserUsecaseImpl(repository)

    @Provides
    fun provideUserLoggedUsecase(repository: DataRepository): UserLoggedUsecase = UserLoggedUsecaseImpl(repository)
}
