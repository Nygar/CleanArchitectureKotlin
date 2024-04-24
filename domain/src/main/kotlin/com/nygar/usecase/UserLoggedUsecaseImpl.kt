package com.nygar.usecase

import cleancode.repository.DataRepository
import com.nygar.model.UserLoggedModel
import com.nygar.model.mappers.MapperUserLogged
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserLoggedUsecaseImpl
    @Inject
    constructor(
        private val repository: DataRepository,
    ) : UserLoggedUsecase {
        override fun getUserLoggedUsecase(): Flow<Result<UserLoggedModel>> =
            flow {
                emit(repository.userLogged().map { MapperUserLogged.transform(it) })
            }
    }
