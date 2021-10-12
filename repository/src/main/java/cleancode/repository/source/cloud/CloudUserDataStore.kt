package cleancode.repository.source.cloud

import cleancode.database.api.UserCache
import cleancode.entity.UserEntity
import cleancode.net.RestApi
import cleancode.repository.source.UserDataStore
import io.reactivex.Observable

/**
 * Construct a [UserDataStore] based on connections to the api (Cloud).
 *
 * @param restApi The [RestApi] implementation to use.
 * @param userCache A [UserCache] to cache data retrieved from the api.
 */
class CloudUserDataStore(private val restApi: RestApi, private val userCache: UserCache) :
    UserDataStore {

    override fun userEntityList(): Observable<List<UserEntity>> {
        return this.restApi.userEntityList()
    }

    override fun userEntityDetails(userId: Int): Observable<UserEntity> {
        return this.restApi.userEntityById(userId).doOnNext( userCache::put)
    }
}