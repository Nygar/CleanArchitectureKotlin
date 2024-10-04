package cleancode.repository.source.cloud

import cleancode.database.api.UserLoggedCache
import cleancode.entity.UserLoggedEntity
import cleancode.net.RestApi
import cleancode.repository.source.UserLoggedDataStore

/**
 * Construct a [UserLoggedDataStore] based on connections to the api (Cloud).
 *
 * @param restApi The [RestApi] implementation to use.
 * @param userLoggedCache A [UserLoggedCache] to cache data retrieved from the api.
 */
class CloudUserLoggedDataStore(private val restApi: RestApi, private val userLoggedCache: UserLoggedCache) :
    UserLoggedDataStore {
    override suspend fun userLoggedEntity(): Result<UserLoggedEntity> {
        return this.restApi.userLoggedEntity().onSuccess(userLoggedCache::put)
    }
}
