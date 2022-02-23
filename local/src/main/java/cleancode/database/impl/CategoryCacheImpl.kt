package cleancode.database.impl

import android.content.Context
import cleancode.database.AppDatabase
import cleancode.database.FileManager
import cleancode.database.api.CategoryCache
import cleancode.entity.CategoryEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CategoryCacheImpl @Inject constructor(private val context: Context, private val database: AppDatabase): CategoryCache {

    companion object{
        private const val SETTINGS_KEY = "CATEGORY"
        private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
    }

    private val fileManager: FileManager = FileManager

    override operator fun get(categoryId: Int): Observable<CategoryEntity> {
        return Observable.create { subscriber ->
            val data = database.categoryCacheDao().getById(categoryId)
            subscriber.onNext(data)
            subscriber.onComplete()
        }
    }

    override fun put(categoryEntity: CategoryEntity) {
        if (!isCached(categoryEntity.categoryId)) {
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