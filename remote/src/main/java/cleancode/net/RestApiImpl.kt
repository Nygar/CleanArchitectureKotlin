package cleancode.net

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.entity.UserEntity
import cleancode.entity.UserLoggedEntity
import cleancode.errors.Error
import cleancode.net.RestEndPoint.Companion.API_BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *  [RestApi] implementation for retrieving data from the network.
 */
class RestApiImpl (context: Context) : RestApi {

    private var client = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .client(client)
        .build()

    private var retrofitAPI: RestEndPoint =  retrofit.create(RestEndPoint::class.java)


    /*USERS*/
    override suspend fun userEntityList(): Result<List<UserEntity>> {
        return suspendCoroutine { result ->
            retrofitAPI.userEntityList().enqueue(object :
                Callback<List<UserEntity>> {
                override fun onResponse(call: Call<List<UserEntity>>, response: Response<List<UserEntity>>) {
                    response.body()?.let { body ->
                        result.resume(Result.success(body))
                    } ?: run {
                        result.resumeWithException(Error.NetworkError())
                    }
                }

                override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                    result.resumeWithException(t)
                }
            })
        }
    }

    override suspend fun userEntityById(userId: Int): Result<UserEntity> {
        return suspendCoroutine { result ->
            retrofitAPI.userEntityById(userId).enqueue(object :
                Callback<UserEntity> {
                override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                    response.body()?.let { body ->
                        result.resume(Result.success(body))
                    } ?: run {
                        result.resumeWithException(Error.NetworkError())
                    }
                }

                override fun onFailure(call: Call<UserEntity>, t: Throwable) {
                    result.resumeWithException(t)
                }
            })
        }
    }

    /*USER LOGGED*/
    override suspend fun userLoggedEntity(): Result<UserLoggedEntity> {
        return suspendCoroutine { result ->
            retrofitAPI.userLoggedEntity().enqueue(object :
                Callback<UserLoggedEntity> {
                override fun onResponse(call: Call<UserLoggedEntity>, response: Response<UserLoggedEntity>) {
                    response.body()?.let { body ->
                        result.resume(Result.success(body))
                    } ?: run {
                        result.resumeWithException(Error.NetworkError())
                    }
                }

                override fun onFailure(call: Call<UserLoggedEntity>, t: Throwable) {
                    result.resumeWithException(t)
                }
            })
        }
    }

    /*MESSAGES*/
    override suspend fun messageEntityList(): Result<List<MessageEntity>> {
        return suspendCoroutine { result ->
            retrofitAPI.messageEntityList().enqueue(object :
                Callback<List<MessageEntity>> {
                override fun onResponse(call: Call<List<MessageEntity>>, response: Response<List<MessageEntity>>) {
                    response.body()?.let { body ->
                        result.resume(Result.success(body))
                    } ?: run {
                        result.resumeWithException(Error.NetworkError())
                    }
                }

                override fun onFailure(call: Call<List<MessageEntity>>, t: Throwable) {
                    result.resumeWithException(t)
                }
            })
        }
    }

    override suspend fun messageEntityById(messageId: Int): Result<MessageEntity> {
        return suspendCoroutine { result ->
            retrofitAPI.messageEntityById(messageId).enqueue(object :
                Callback<MessageEntity> {
                override fun onResponse(call: Call<MessageEntity>, response: Response<MessageEntity>) {
                    response.body()?.let { body ->
                        result.resume(Result.success(body))
                    } ?: run {
                        result.resumeWithException(Error.NetworkError())
                    }
                }

                override fun onFailure(call: Call<MessageEntity>, t: Throwable) {
                    result.resumeWithException(t)
                }
            })
        }
    }

    /*CATEGORIES*/
    override suspend fun categoryEntityList(): Result<List<CategoryEntity>> {
        return suspendCoroutine { result ->
            retrofitAPI.categoryEntityList().enqueue(object :
                Callback<List<CategoryEntity>> {
                override fun onResponse(call: Call<List<CategoryEntity>>, response: Response<List<CategoryEntity>>) {
                    response.body()?.let { body ->
                        result.resume(Result.success(body))
                    } ?: run {
                        result.resumeWithException(Error.NetworkError())
                    }
                }

                override fun onFailure(call: Call<List<CategoryEntity>>, t: Throwable) {
                    result.resumeWithException(t)
                }
            })
        }
    }

    override suspend fun categoryEntityById(categoryId: Int): Result<CategoryEntity> {
        return suspendCoroutine { result ->
            retrofitAPI.categoryEntityById(categoryId).enqueue(object :
                Callback<CategoryEntity> {
                override fun onResponse(call: Call<CategoryEntity>, response: Response<CategoryEntity>) {
                    response.body()?.let { body ->
                        result.resume(Result.success(body))
                    } ?: run {
                        result.resumeWithException(Error.NetworkError())
                    }
                }

                override fun onFailure(call: Call<CategoryEntity>, t: Throwable) {
                    result.resumeWithException(t)
                }
            })
        }
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}