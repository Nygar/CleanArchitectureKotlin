package cleancode.database.impl

import android.content.Context
import cleancode.database.AppDatabase
import cleancode.database.FileManager
import cleancode.database.api.UserCache
import cleancode.entity.UserEntity
import io.reactivex.Observable
import javax.inject.Inject

class UserCacheImpl @Inject constructor(private val context: Context, private val database: AppDatabase): UserCache {

    private val SETTINGS_KEY = "USER"
    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private val fileManager: FileManager = FileManager

    override operator fun get(userId: Int): Observable<UserEntity> {
        return Observable.create { subscriber ->
            val userEntity = database.userCacheDao().getById(userId)
            subscriber.onNext(userEntity)
            subscriber.onComplete()
        }
    }

    override fun put(userEntity: UserEntity) {
        if (!isCached(userEntity.userId)) {
            database.userCacheDao().insertSingle(userEntity)
            setLastCacheUpdateTimeMillis()
        }

    }

    override fun isCached(userId: Int): Boolean {
        var res = false
        val result: UserEntity? = database.userCacheDao().getById(userId)
        if (result != null) {
            res = true
        }
        return res
    }

    override fun isExpired(userId: Int): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()

        val expired = currentTime - lastUpdateTime > EXPIRATION_TIME

        if (expired) {
            this.evictAll(userId)
        }
        return expired
    }

    override fun evictAll(userId: Int) {
        database.userCacheDao().getSingleRecordDelete(userId)
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private fun setLastCacheUpdateTimeMillis() {
        val currentMillis = System.currentTimeMillis()
        this.fileManager.writeToPreferences(this.context, SETTINGS_KEY, currentMillis)
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_KEY)
    }

}