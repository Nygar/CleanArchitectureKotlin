package cleancode.usecase

import cleancode.model.UserLoggedModel
import cleancode.model.mappers.MapperUserLogged
import cleancode.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserLoggedUsecaseImpl @Inject constructor(
    private val repository: DataRepository
) : UserLoggedUsecase {
    override fun getUserLoggedUsecase(): Flow<Result<UserLoggedModel>> =
        flow {
            emit(repository.userLogged().map { MapperUserLogged.transform(it) })
        }
}