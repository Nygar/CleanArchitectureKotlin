package cleancode.net

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import cleancode.entity.CategoryEntity
import cleancode.entity.MessageEntity
import cleancode.entity.UserEntity
import cleancode.entity.UserLoggedEntity
import cleancode.net.RestEndPoint.Companion.API_BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.rxjava3.core.Observable
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import okhttp3.OkHttpClient

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

    var retrofitAPI: RestEndPoint =  retrofit.create(RestEndPoint::class.java)


    /*USERS*/
    override fun userEntityList(): Observable<List<UserEntity>> {
        return Observable.create { subscriber ->
            retrofitAPI.userEntityList().enqueue(object :
                Callback<List<UserEntity>> {
                override fun onResponse(call: Call<List<UserEntity>>, response: Response<List<UserEntity>>) {
                    subscriber.onNext(response.body()!!)
                    subscriber.onComplete()
                }

                override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                    subscriber.onError(t)
                }
            })
        }
    }

    override fun userEntityById(userId: Int): Observable<UserEntity> {
        return Observable.create { subscriber ->
            retrofitAPI.userEntityById(userId).enqueue(object :
                Callback<UserEntity> {
                override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                    subscriber.onNext(response.body()!!)
                    subscriber.onComplete()
                }

                override fun onFailure(call: Call<UserEntity>, t: Throwable) {
                    subscriber.onError(t)
                }
            })
        }
    }

    /*USER LOGGED*/
    override fun userLoggedEntity(): Observable<UserLoggedEntity> {
        return Observable.create { subscriber ->
            retrofitAPI.userLoggedEntity().enqueue(object :
                Callback<UserLoggedEntity> {
                override fun onResponse(call: Call<UserLoggedEntity>, response: Response<UserLoggedEntity>) {
                    subscriber.onNext(response.body()!!)
                    subscriber.onComplete()
                }

                override fun onFailure(call: Call<UserLoggedEntity>, t: Throwable) {
                    subscriber.onError(t)
                }
            })
        }
    }

    /*MESSAGES*/
    override fun messageEntityList(): Observable<List<MessageEntity>> {
        return Observable.create { subscriber ->
            retrofitAPI.messageEntityList().enqueue(object :
                Callback<List<MessageEntity>> {
                override fun onResponse(call: Call<List<MessageEntity>>, response: Response<List<MessageEntity>>) {
                    subscriber.onNext(response.body()!!)
                    subscriber.onComplete()
                }

                override fun onFailure(call: Call<List<MessageEntity>>, t: Throwable) {
                    subscriber.onError(t)
                }
            })
        }
    }

    override fun messageEntityById(messageId: Int): Observable<MessageEntity> {
        return Observable.create { subscriber ->
            retrofitAPI.messageEntityById(messageId).enqueue(object :
                Callback<MessageEntity> {
                override fun onResponse(call: Call<MessageEntity>, response: Response<MessageEntity>) {
                    subscriber.onNext(response.body()!!)
                    subscriber.onComplete()
                }

                override fun onFailure(call: Call<MessageEntity>, t: Throwable) {
                    subscriber.onError(t)
                }
            })
        }
    }

    /*CATEGORIES*/
    override fun categoryEntityList(): Observable<List<CategoryEntity>> {
        return Observable.create { subscriber ->
            retrofitAPI.categoryEntityList().enqueue(object :
                Callback<List<CategoryEntity>> {
                override fun onResponse(call: Call<List<CategoryEntity>>, response: Response<List<CategoryEntity>>) {
                    subscriber.onNext(response.body()!!)
                    subscriber.onComplete()
                }

                override fun onFailure(call: Call<List<CategoryEntity>>, t: Throwable) {
                    subscriber.onError(t)
                }
            })
        }
    }

    override fun categoryEntityById(categoryId: Int): Observable<CategoryEntity> {
        return Observable.create { subscriber ->
            retrofitAPI.categoryEntityById(categoryId).enqueue(object :
                Callback<CategoryEntity> {
                override fun onResponse(call: Call<CategoryEntity>, response: Response<CategoryEntity>) {
                    subscriber.onNext(response.body()!!)
                    subscriber.onComplete()
                }

                override fun onFailure(call: Call<CategoryEntity>, t: Throwable) {
                    subscriber.onError(t)
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