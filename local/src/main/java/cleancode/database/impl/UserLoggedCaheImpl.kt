package cleancode.database.impl

import android.content.Context
import cleancode.database.AppDatabase
import cleancode.database.FileManager
import cleancode.database.api.UserLoggedCache
import cleancode.entity.UserLoggedEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserLoggedCacheImpl @Inject constructor(private val context: Context, private val database: AppDatabase) : UserLoggedCache {

    companion object {
        private const val SETTINGS_KEY = "USERLOGGED"
        private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
    }

    private val fileManager: FileManager = FileManager

    override fun get(): Observable<UserLoggedEntity> {
        return Observable.create { subscriber ->
            val userLoggedEntity = database.userLoggedCacheDao().getById()
            subscriber.onNext(userLoggedEntity)
            subscriber.onComplete()

        }
    }

    override fun put(userLoggedEntity: UserLoggedEntity) {
        if (!isCached()) {
            database.userLoggedCacheDao().insertSingle(userLoggedEntity)
            setLastCacheUpdateTimeMillis()
        }
    }

    override fun isCached(): Boolean {
        var res = false
        val userLoggedEntity: UserLoggedEntity? = database.userLoggedCacheDao().getById()
        if (userLoggedEntity != null) {
            res = true
        }
        return res
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()

        val expired = currentTime - lastUpdateTime > EXPIRATION_TIME

        if (expired) {
            this.evictAll()
        }

        return expired
    }

    override fun evictAll() {
        database.userLoggedCacheDao().getSingleRecordDelete()
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private fun setLastCacheUpdateTimeMillis() {
        val currentMillis = System.currentTimeMillis()
        fileManager.writeToPreferences(context, SETTINGS_KEY, currentMillis)
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return fileManager.getFromPreferences(context, SETTINGS_KEY)
    }
}