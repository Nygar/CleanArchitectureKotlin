package cleancode.database.impl

import android.content.Context
import cleancode.database.AppDatabase
import cleancode.database.FileManager
import cleancode.database.api.CategoryCache
import com.nygar.entity.CategoryEntity
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CategoryCacheImpl
    @Inject
    constructor(private val context: Context, private val database: AppDatabase) : CategoryCache {
        companion object {
            private const val SETTINGS_KEY = "CATEGORY"
            private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
        }

        private val fileManager: FileManager = FileManager

        override suspend fun get(categoryId: Int): Result<CategoryEntity> {
            return suspendCoroutine {
                val data = database.categoryCacheDao().getById(categoryId)
                it.resume(Result.success(data))
            }
        }

        override fun put(categoryEntity: CategoryEntity) {
            if (!isCached(categoryEntity.id)) {
                database.categoryCacheDao().insertSingle(categoryEntity)
                setLastCacheUpdateTimeMillis()
            }
        }

        override fun isCached(categoryId: Int): Boolean {
            var res = false
            val result: CategoryEntity? = database.categoryCacheDao().getById(categoryId)
            if (result != null) {
                res = true
            }
            return res
        }

        override fun isExpired(categoryId: Int): Boolean {
            val currentTime = System.currentTimeMillis()
            val lastUpdateTime = getLastCacheUpdateTimeMillis()

            val expired = currentTime - lastUpdateTime > EXPIRATION_TIME

            if (expired) {
                evictAll(categoryId)
            }

            return expired
        }

        override fun evictAll(categoryId: Int) {
            database.categoryCacheDao().getSingleRecordDelete(categoryId)
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
