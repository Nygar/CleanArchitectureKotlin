package cleancode.repository

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
import javax.inject.Inject

class DataRepositoryImpl
    @Inject
    constructor(
        private val restApi: RestApi,
        private val categoryCache: CategoryCache,
        private val messageCache: MessageCache,
        private val userCache: UserCache,
        private val userLoggedCache: UserLoggedCache,
    ) : DataRepository {
        override suspend fun categories(): Result<List<CategoryEntity>> {
            // we always get all messages from the cloud
            val dataStore = CloudCategoryDataStore(restApi, categoryCache)
            return dataStore.categoryEntityList()
        }

        override suspend fun category(categoryId: Int): Result<CategoryEntity> {
            val dataStore: CategoryDataStore =
                if (!categoryCache.isExpired(categoryId) && categoryCache.isCached(categoryId)) {
                    DiskCategoryDataStore(categoryCache)
                } else {
                    CloudCategoryDataStore(restApi, categoryCache)
                }
            return dataStore.categoryEntityDetails(categoryId)
        }

        override suspend fun messages(): Result<List<MessageEntity>> {
            // we always get all messages from the cloud
            val dataStore = CloudMessageDataStore(restApi, messageCache)
            return dataStore.messageEntityList()
        }

        override suspend fun message(messageId: Int): Result<MessageEntity> {
            val dataStore: MessageDataStore =
                if (!messageCache.isExpired(messageId) && messageCache.isCached(messageId)) {
                    DiskMessageDataStore(messageCache)
                } else {
                    CloudMessageDataStore(restApi, messageCache)
                }
            return dataStore.messageEntityDetails(messageId)
        }

        override suspend fun users(): Result<List<UserEntity>> {
            // we always get all messages from the cloud
            val dataStore = CloudUserDataStore(restApi, userCache)
            return dataStore.userEntityList()
        }

        override suspend fun user(userId: Int): Result<UserEntity> {
            val dataStore: UserDataStore =
                if (!userCache.isExpired(userId) && userCache.isCached(userId)) {
                    DiskUserDataStore(userCache)
                } else {
                    CloudUserDataStore(restApi, userCache)
                }
            return dataStore.userEntityDetails(userId)
        }

        override suspend fun userLogged(): Result<UserLoggedEntity> {
            val dataStore: UserLoggedDataStore =
                if (!userLoggedCache.isExpired() && userLoggedCache.isCached()) {
                    DiskUserLoggedDataStore(userLoggedCache)
                } else {
                    CloudUserLoggedDataStore(restApi, userLoggedCache)
                }
            return dataStore.userLoggedEntity()
        }
    }
