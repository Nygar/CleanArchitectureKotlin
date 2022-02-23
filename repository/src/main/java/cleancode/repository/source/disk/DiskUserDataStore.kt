package cleancode.repository.source.disk

import cleancode.entity.UserEntity
import cleancode.database.api.UserCache
import cleancode.repository.source.UserDataStore
import io.reactivex.rxjava3.core.Observable

/**
 * Construct a [UserDataStore] based file system data store.
 *
 * @param userCache A [UserCache] to cache data retrieved from the api.
 */
class DiskUserDataStore(private val userCache: UserCache) : UserDataStore {

    override fun userEntityList(): Observable<List<UserEntity>> {
        //TODO: implement simple cache for storing/retrieving collections of users.
        throw UnsupportedOperationException("Operation is not available!!!")
    }

    override fun userEntityDetails(userId: Int): Observable<UserEntity> {
        return userCache[userId]
    }
}
