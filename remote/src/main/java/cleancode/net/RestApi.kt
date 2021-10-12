package cleancode.net

import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.entity.UserEntity
import cleancode.entity.UserLoggedEntity
import io.reactivex.Observable

/**
 * RestApi for retrieving data from the network.
 */
interface RestApi {
    /**
     * Retrieves an [Observable] which will emit a [UserEntity].
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     *
     * @param userId The user id used to get user data.
     */
    fun userEntityById(userId: Int): Observable<UserEntity>

    /**
     * Retrieves an [Observable] which will emit a List of [UserEntity].
     * Api url for getting all users
     */
    fun userEntityList(): Observable<List<UserEntity>>

    /**
     * Retrieves an [Observable] which will emit a [UserLoggedEntity].
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     */
    fun userLoggedEntity(): Observable<UserLoggedEntity>

    /**
     * Retrieves an [Observable] which will emit a List of [MessageEntity].
     * Api url for getting all messages
     */
    fun messageEntityList(): Observable<List<MessageEntity>>

    /**
     * Retrieves an [Observable] which will emit a [MessageEntity].
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param messageId The user id used to get user data.
     */
    fun messageEntityById(messageId: Int): Observable<MessageEntity>


    /**
     * Retrieves an [Observable] which will emit a List of [MessageEntity].
     * Api url for getting all messages
     */
    fun categoryEntityList(): Observable<List<CategoryEntity>>

    /**
     * Retrieves an [Observable] which will emit a [MessageEntity].
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param categoryId The user id used to get user data.
     */
    fun categoryEntityById(categoryId: Int): Observable<CategoryEntity>
}