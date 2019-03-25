package cuongduong.developer.android.stackoverflow.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import cuongduong.developer.android.stackoverflow.data.network.ConnectivityInterceptor
import cuongduong.developer.android.stackoverflow.data.network.response.UserReputationResponse
import cuongduong.developer.android.stackoverflow.data.network.response.UserListResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.stackexchange.com/2.2/"

interface StackExchangeApiServices {

    // https://api.stackexchange.com/2.2/users/{ids}?order=desc&sort=reputation&site=stackoverflow
    @GET("users/{ids}?order=desc&sort=reputation&site=stackoverflow")
    fun getUser(
        @Path("ids") id: String
    ): Deferred<UserListResponse>

    // https://api.stackexchange.com/2.2/users?page=1&pagesize=30&site=stackoverflow
    @GET("users?site=stackoverflow")
    fun getUserList(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): Deferred<UserListResponse>

    // https://api.stackexchange.com/2.2/users/{userid}/reputation-history?page=1&pagesize=30&site=stackoverflow
    @GET("users/{userid}/reputation-history?site=stackoverflow")
    fun getUserReputation(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): Deferred<UserReputationResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): StackExchangeApiServices {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StackExchangeApiServices::class.java)

        }
    }

}