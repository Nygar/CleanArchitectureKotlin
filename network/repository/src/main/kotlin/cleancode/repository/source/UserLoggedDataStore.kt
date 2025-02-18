package cleancode.repository.source

import com.nygar.entity.UserLoggedEntity

/**
 * Interface that represents a data store from where data is retrieved.
 */
interface UserLoggedDataStore {
    /**
     * Get an [Result] which will emit a [UserLoggedEntity].
     *
     */
    suspend fun userLoggedEntity(): Result<UserLoggedEntity>
}
