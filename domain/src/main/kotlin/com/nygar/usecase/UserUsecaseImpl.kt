package com.nygar.usecase

import cleancode.repository.DataRepository
import com.nygar.model.UserModel
import com.nygar.model.mappers.MapperUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUsecaseImpl
    @Inject
    constructor(
        private val repository: DataRepository,
    ) : UserUsecase {
        override suspend fun getUserListUsecase(): Flow<Result<List<UserModel>>> =
            flow {
                emit(repository.users().map { MapperUser.transform(it) })
            }

        override suspend fun getUserUsecase(userId: Int): Flow<Result<UserModel>> =
            flow {
                emit(repository.user(userId).map { MapperUser.transform(it) })
            }
    }
