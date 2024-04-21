package cleancode.usecase

import cleancode.model.UserModel
import cleancode.model.mappers.MapperUser
import cleancode.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUsecaseImpl @Inject constructor(
    private val repository: DataRepository
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