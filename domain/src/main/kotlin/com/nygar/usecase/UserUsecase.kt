package com.nygar.usecase

import com.nygar.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserUsecase {
    suspend fun getUserListUsecase(): Flow<Result<List<UserModel>>>

    suspend fun getUserUsecase(userId: Int): Flow<Result<UserModel>>
}
