package cleancode.repository.source

import com.nygar.entity.UserEntity

/**
 * Interface that represents a data store from where data is retrieved.
 */
interface UserDataStore {
    /**
     * Get an [Result] which will emit a List of [UserEntity].
     */
    suspend fun userEntityList(): Result<List<UserEntity>>

    /**
     * Get an [Result] which will emit a [UserEntity] by its id.
     *
     * @param userId The id to retrieve user data.
     */
    suspend fun userEntityDetails(userId: Int): Result<UserEntity>
}
