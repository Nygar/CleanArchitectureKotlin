package cleancode.database.impl

import android.content.Context
import cleancode.database.AppDatabase
import cleancode.database.FileManager
import cleancode.database.api.UserCache
import com.nygar.entity.UserEntity
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserCacheImpl
    @Inject
    constructor(private val context: Context, private val database: AppDatabase) : UserCache {
        companion object {
            private const val SETTINGS_KEY = "USER"
            private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
        }

        private val fileManager: FileManager = FileManager

        override suspend fun get(userId: Int): Result<UserEntity> {
            return suspendCoroutine {
                val userEntity = database.userCacheDao().getById(userId)
                it.resume(Result.success(userEntity))
            }
        }

        override fun put(userEntity: UserEntity) {
            if (!isCached(userEntity.id)) {
                database.userCacheDao().insertSingle(userEntity)
                setLastCacheUpdateTimeMillis()
            }
        }

        override suspend fun getList(): Result<List<UserEntity>> {
            return suspendCoroutine {
                val m = database.userCacheDao().getList()
                it.resume(Result.success(m))
            }
        }

        override fun putList(listEntity: List<UserEntity>) {
            database.userCacheDao().insertList(listEntity)
            setLastCacheUpdateTimeMillis()
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
