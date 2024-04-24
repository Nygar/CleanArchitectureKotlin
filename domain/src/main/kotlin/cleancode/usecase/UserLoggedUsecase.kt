package cleancode.usecase

import cleancode.model.UserLoggedModel
import kotlinx.coroutines.flow.Flow

interface UserLoggedUsecase {
    fun getUserLoggedUsecase(): Flow<Result<UserLoggedModel>>
}
