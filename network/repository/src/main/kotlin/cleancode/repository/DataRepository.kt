package cleancode.repository

import com.nygar.entity.CategoryEntity
import com.nygar.entity.MessageEntity
import com.nygar.entity.UserEntity
import com.nygar.entity.UserLoggedEntity

interface DataRepository {
    /**
     * Get an [Result] which will emit a List of [CategoryEntity].
     */
    suspend fun categories(): Result<List<CategoryEntity>>

    /**
     * Get an [Result] which will emit a [CategoryEntity].
     *
     * @param categoryId The user id used to retrieve user data.
     */
    suspend fun category(categoryId: Int): Result<CategoryEntity>

    /**
     * Get an [Result] which will emit a List of [MessageEntity].
     */
    suspend fun messages(): Result<List<MessageEntity>>

    /**
     * Get an [Result] which will emit a [MessageEntity].
     *
     * @param messageId The user id used to retrieve user data.
     */
    suspend fun message(messageId: Int): Result<MessageEntity>

    /**
     * Get an [Result] which will emit a List of [UserEntity].
     */
    suspend fun users(): Result<List<UserEntity>>

    /**
     * Get an [Result] which will emit a [UserEntity].
     *
     * @param userId The user id used to retrieve user data.
     */
    suspend fun user(userId: Int): Result<UserEntity>

    /**
     * Get an [Result] which will emit a [UserLoggedEntity].
     *
     */
    suspend fun userLogged(): Result<UserLoggedEntity>
}
