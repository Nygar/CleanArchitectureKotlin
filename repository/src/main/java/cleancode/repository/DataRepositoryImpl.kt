package cleancode.repository

import android.content.Context
import cleancode.database.api.CategoryCache
import cleancode.database.api.MessageCache
import cleancode.database.api.UserCache
import cleancode.database.api.UserLoggedCache
import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.entity.UserEntity
import cleancode.entity.UserLoggedEntity
import cleancode.net.RestApi
import cleancode.repository.source.CategoryDataStore
import cleancode.repository.source.MessageDataStore
import cleancode.repository.source.UserDataStore
import cleancode.repository.source.UserLoggedDataStore
import cleancode.repository.source.cloud.CloudCategoryDataStore
import cleancode.repository.source.cloud.CloudMessageDataStore
import cleancode.repository.source.cloud.CloudUserDataStore
import cleancode.repository.source.cloud.CloudUserLoggedDataStore
import cleancode.repository.source.disk.DiskCategoryDataStore
import cleancode.repository.source.disk.DiskMessageDataStore
import cleancode.repository.source.disk.DiskUserDataStore
import cleancode.repository.source.disk.DiskUserLoggedDataStore
import io.reactivex.Observable
import javax.inject.Inject

class DataRepositoryImpl
@Inject constructor(private val context: Context,
                    private val restApi: RestApi,
                    private val categoryCache: CategoryCache,
                    private val messageCache: MessageCache,
                    private val userCache: UserCache,
                    private val userLoggedCache: UserLoggedCache
): DataRepository {

    override fun categories(): Observable<List<CategoryEntity>> {
        //we always get all messages from the cloud
        val dataStore = CloudCategoryDataStore(restApi, categoryCache)
        return dataStore.categoryEntityList()
    }

    override fun category(categoryId: Int): Observable<CategoryEntity> {
        val dataStore: CategoryDataStore
        if (!categoryCache.isExpired(categoryId) && categoryCache.isCached(categoryId)) {
            dataStore = DiskCategoryDataStore(categoryCache)
        } else {
            dataStore = CloudCategoryDataStore(restApi, categoryCache)
        }
        return dataStore.categoryEntityDetails(categoryId)
    }

    override fun messages(): Observable<List<MessageEntity>> {
        //we always get all messages from the cloud
        val dataStore = CloudMessageDataStore(restApi, messageCache)
        return dataStore.messageEntityList()
    }

    override fun message(messageId: Int): Observable<MessageEntity> {
        val dataStore: MessageDataStore
        if (!messageCache.isExpired(messageId) && messageCache.isCached(messageId)) {
            dataStore = DiskMessageDataStore(messageCache)
        } else {
            dataStore = CloudMessageDataStore(restApi, messageCache)
        }
        return dataStore.messageEntityDetails(messageId)
    }

    override fun users(): Observable<List<UserEntity>> {
        //we always get all messages from the cloud
        val dataStore = CloudUserDataStore(restApi, userCache)
        return dataStore.userEntityList()
    }

    override fun user(userId: Int): Observable<UserEntity> {
        val dataStore: UserDataStore
        if (!userCache.isExpired(userId) && userCache.isCached(userId)) {
            dataStore = DiskUserDataStore(userCache)
        } else {
            dataStore = CloudUserDataStore(restApi, userCache)
        }
        return dataStore.userEntityDetails(userId)
    }

    override fun userLogged(): Observable<UserLoggedEntity> {
        val dataStore: UserLoggedDataStore
        if (!userLoggedCache.isExpired() && userLoggedCache.isCached()) {
            dataStore = DiskUserLoggedDataStore(userLoggedCache)
        } else {
            dataStore = CloudUserLoggedDataStore(restApi, userLoggedCache)
        }
        return dataStore.userLoggedEntity()
    }
}