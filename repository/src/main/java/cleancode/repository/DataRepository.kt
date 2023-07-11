package cleancode.repository

import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.entity.UserEntity
import cleancode.entity.UserLoggedEntity
import io.reactivex.rxjava3.core.Observable

interface DataRepository {

    /**
     * Get an [Observable] which will emit a List of [CategoryEntity].
     */
    suspend fun categories(): Result<List<CategoryEntity>>

    /**
     * Get an [Observable] which will emit a [CategoryEntity].
     *
     * @param categoryId The user id used to retrieve user data.
     */
    suspend fun category(categoryId: Int): Result<CategoryEntity>

    /**
     * Get an [Observable] which will emit a List of [MessageEntity].
     */
    suspend fun messages(): Result<List<MessageEntity>>

    /**
     * Get an [Observable] which will emit a [MessageEntity].
     *
     * @param messageId The user id used to retrieve user data.
     */
    suspend fun message(messageId: Int): Result<MessageEntity>

    /**
     * Get an [Observable] which will emit a List of [UserEntity].
     */
    suspend fun users(): Result<List<UserEntity>>

    /**
     * Get an [Observable] which will emit a [UserEntity].
     *
     * @param userId The user id used to retrieve user data.
     */
    suspend fun user(userId: Int): Result<UserEntity>

    /**
     * Get an [Observable] which will emit a [UserLoggedEntity].
     *
     */
    suspend fun userLogged(): Result<UserLoggedEntity>

}