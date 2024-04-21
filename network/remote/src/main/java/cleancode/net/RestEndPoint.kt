package cleancode.net

import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.entity.UserEntity
import cleancode.entity.UserLoggedEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestEndPoint {

    /**
     * Retrieves an [Result] which will emit a [UserEntity].
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     *
     * @param userId The user id used to get user data.
     */
    @GET("user_{userId}.json")
    fun userEntityById(@Path("userId") userId: Int): Call<UserEntity>

    /**
     * Retrieves an [Result] which will emit a List of [UserEntity].
     * Api url for getting all users
     */
    @GET("users.json")
    fun userEntityList(): Call<List<UserEntity>>

    /**
     * Retrieves an [Result] which will emit a [UserLoggedEntity].
     * Api url for getting a user profile: Remember to concatenate id + 'json'
     */
    @GET("user_logged.json")
    fun userLoggedEntity(): Call<UserLoggedEntity>

    /**
     * Retrieves an [Result] which will emit a List of [MessageEntity].
     * Api url for getting all messages
     */
    @GET("messages.json")
    fun messageEntityList(): Call<List<MessageEntity>>

    /**
     * Retrieves an [Result] which will emit a [MessageEntity].
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param messageId The user id used to get user data.
     */
    @GET("message_{messageId}.json")
    fun messageEntityById(@Path("messageId") messageId: Int): Call<MessageEntity>


    /**
     * Retrieves an [Result] which will emit a List of [MessageEntity].
     * Api url for getting all messages
     */
    @GET("categories.json")
    fun categoryEntityList(): Call<List<CategoryEntity>>

    /**
     * Retrieves an [Result] which will emit a [MessageEntity].
     * Api url for getting a message details: Remember to concatenate id + 'json'
     *
     * @param categoryId The user id used to get user data.
     */
    @GET("category_{categoryId}.json")
    fun categoryEntityById(@Path("categoryId") categoryId: Int): Call<CategoryEntity>

}