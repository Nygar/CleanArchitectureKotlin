package cleancode.repository.source.disk

import cleancode.entity.UserLoggedEntity
import cleancode.database.api.UserLoggedCache
import cleancode.repository.source.UserLoggedDataStore
import io.reactivex.rxjava3.core.Observable

/**
 * Construct a [UserLoggedDataStore] based file system data store.
 *
 * @param userLoggedCache A [UserLoggedCache] to cache data retrieved from the api.
 */
class DiskUserLoggedDataStore(private val userLoggedCache: UserLoggedCache) :
    UserLoggedDataStore {

    override fun userLoggedEntity(): Observable<UserLoggedEntity> {
        return userLoggedCache.get()
    }
}
