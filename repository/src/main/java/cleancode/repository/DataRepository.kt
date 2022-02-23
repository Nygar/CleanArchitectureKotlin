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
    fun categories(): Observable<List<CategoryEntity>>

    /**
     * Get an [Observable] which will emit a [CategoryEntity].
     *
     * @param categoryId The user id used to retrieve user data.
     */
    fun category(categoryId: Int): Observable<CategoryEntity>

    /**
     * Get an [Observable] which will emit a List of [MessageEntity].
     */
    fun messages(): Observable<List<MessageEntity>>

    /**
     * Get an [Observable] which will emit a [MessageEntity].
     *
     * @param messageId The user id used to retrieve user data.
     */
    fun message(messageId: Int): Observable<MessageEntity>

    /**
     * Get an [Observable] which will emit a List of [UserEntity].
     */
    fun users(): Observable<List<UserEntity>>

    /**
     * Get an [Observable] which will emit a [UserEntity].
     *
     * @param userId The user id used to retrieve user data.
     */
    fun user(userId: Int): Observable<UserEntity>

    /**
     * Get an [Observable] which will emit a [UserLoggedEntity].
     *
     */
    fun userLogged(): Observable<UserLoggedEntity>

}