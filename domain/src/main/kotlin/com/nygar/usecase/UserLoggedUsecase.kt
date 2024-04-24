package com.nygar.usecase

import com.nygar.model.UserLoggedModel
import kotlinx.coroutines.flow.Flow

interface UserLoggedUsecase {
    fun getUserLoggedUsecase(): Flow<Result<UserLoggedModel>>
}
