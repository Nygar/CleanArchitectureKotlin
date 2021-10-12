package cleancode.database.impl

import android.content.Context
import cleancode.database.AppDatabase
import cleancode.database.FileManager
import cleancode.database.api.MessageCache
import cleancode.entity.MessageEntity
import io.reactivex.Observable
import javax.inject.Inject

class MessageCacheImpl @Inject constructor(private val context: Context, private val database: AppDatabase): MessageCache {

    private val SETTINGS_KEY = "MESSAGE"
    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private val fileManager: FileManager = FileManager

    override operator fun get(messageId: Int): Observable<MessageEntity> {

        return Observable.create { subscriber ->
            val m = database.messageCacheDao().getById(messageId)
            subscriber.onNext(m)
            subscriber.onComplete()
        }
    }

    override fun put(messageEntity: MessageEntity) {
        if (!isCached(messageEntity.messageId)) {
            database.messageCacheDao().insertSingle(messageEntity)
            setLastCacheUpdateTimeMillis()
        }
    }

    override fun isCached(messageId: Int): Boolean {
        var res = false
        val result: MessageEntity? = database.messageCacheDao().getById(messageId)
        if (result != null) {
            res = true
        }
        return res
    }

    override fun isExpired(messageId: Int): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()

        val expired = currentTime - lastUpdateTime > EXPIRATION_TIME

        if (expired) {
            this.evictAll(messageId)
        }

        return expired
    }

    override fun evictAll(messageId: Int) {
        database.messageCacheDao().getSingleRecordDelete(messageId)
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