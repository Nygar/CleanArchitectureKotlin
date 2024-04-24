package cleancode.repository.source.disk

import cleancode.database.api.UserLoggedCache
import cleancode.repository.source.UserLoggedDataStore
import com.nygar.entity.UserLoggedEntity

/**
 * Construct a [UserLoggedDataStore] based file system data store.
 *
 * @param userLoggedCache A [UserLoggedCache] to cache data retrieved from the api.
 */
class DiskUserLoggedDataStore(private val userLoggedCache: UserLoggedCache) :
    UserLoggedDataStore {
    override suspend fun userLoggedEntity(): Result<UserLoggedEntity> {
        return userLoggedCache.get()
    }
}
