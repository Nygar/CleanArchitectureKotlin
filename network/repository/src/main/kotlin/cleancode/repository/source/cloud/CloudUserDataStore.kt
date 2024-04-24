package cleancode.repository.source.cloud

import cleancode.database.api.UserCache
import cleancode.net.RestApi
import cleancode.repository.source.UserDataStore
import com.nygar.entity.UserEntity

/**
 * Construct a [UserDataStore] based on connections to the api (Cloud).
 *
 * @param restApi The [RestApi] implementation to use.
 * @param userCache A [UserCache] to cache data retrieved from the api.
 */
class CloudUserDataStore(private val restApi: RestApi, private val userCache: UserCache) :
    UserDataStore {
    override suspend fun userEntityList(): Result<List<UserEntity>> {
        return this.restApi.userEntityList()
    }

    override suspend fun userEntityDetails(userId: Int): Result<UserEntity> {
        return this.restApi.userEntityById(userId).onSuccess(userCache::put)
    }
}
