package cleancode.repository.source

import cleancode.entity.UserLoggedEntity
import io.reactivex.rxjava3.core.Observable


/**
 * Interface that represents a data store from where data is retrieved.
 */
interface UserLoggedDataStore {
    /**
     * Get an [Observable] which will emit a [UserLoggedEntity].
     *
     */
    fun userLoggedEntity(): Observable<UserLoggedEntity>
}