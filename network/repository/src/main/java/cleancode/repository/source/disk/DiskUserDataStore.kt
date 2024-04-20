package cleancode.repository.source.disk

import cleancode.entity.UserEntity
import cleancode.database.api.UserCache
import cleancode.repository.source.UserDataStore

/**
 * Construct a [UserDataStore] based file system data store.
 *
 * @param userCache A [UserCache] to cache data retrieved from the api.
 */
class DiskUserDataStore(private val userCache: UserCache) : UserDataStore {

    override suspend fun userEntityList(): Result<List<UserEntity>> {
        return userCache.getList()
    }

    override suspend fun userEntityDetails(userId: Int): Result<UserEntity> {
        return userCache.get(userId)
    }
}