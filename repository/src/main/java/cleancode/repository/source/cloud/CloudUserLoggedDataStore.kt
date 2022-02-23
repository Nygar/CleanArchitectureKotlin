package cleancode.repository.source.cloud

import cleancode.entity.UserLoggedEntity
import cleancode.database.api.UserLoggedCache
import cleancode.net.RestApi
import cleancode.repository.source.UserLoggedDataStore
import io.reactivex.rxjava3.core.Observable

/**
 * Construct a [UserLoggedDataStore] based on connections to the api (Cloud).
 *
 * @param restApi The [RestApi] implementation to use.
 * @param userLoggedCache A [UserLoggedCache] to cache data retrieved from the api.
 */
class CloudUserLoggedDataStore(private val restApi: RestApi, private val userLoggedCache: UserLoggedCache) :
    UserLoggedDataStore {

    override fun userLoggedEntity(): Observable<UserLoggedEntity> {
        return this.restApi.userLoggedEntity().doOnNext(userLoggedCache::put)
    }
}