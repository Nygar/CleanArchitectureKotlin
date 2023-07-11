package cleancode.net

import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.entity.UserEntity
import cleancode.entity.UserLoggedEntity

/**
 * RestApi for retrieving data from the network.
 */
interface RestApi {
    /**
     * Retrieves an [Result] which will emit a [UserEntity].
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     *
     * @param userId The user id used to get user data.
     */
    suspend fun userEntityById(userId: Int): Result<UserEntity>

    /**
     * Retrieves an [Result] which will emit a List of [UserEntity].
     * Api url for getting all users
     */
    suspend fun userEntityList(): Result<List<UserEntity>>

    /**
     * Retrieves an [Result] which will emit a [UserLoggedEntity].
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     */
    suspend fun userLoggedEntity(): Result<UserLoggedEntity>

    /**
     * Retrieves an [Result] which will emit a List of [MessageEntity].
     * Api url for getting all messages
     */
    suspend fun messageEntityList(): Result<List<MessageEntity>>

    /**
     * Retrieves an [Result] which will emit a [MessageEntity].
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param messageId The user id used to get user data.
     */
    suspend fun messageEntityById(messageId: Int): Result<MessageEntity>


    /**
     * Retrieves an [Result] which will emit a List of [MessageEntity].
     * Api url for getting all messages
     */
    suspend fun categoryEntityList(): Result<List<CategoryEntity>>

    /**
     * Retrieves an [Result] which will emit a [MessageEntity].
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param categoryId The user id used to get user data.
     */
    suspend fun categoryEntityById(categoryId: Int): Result<CategoryEntity>
}