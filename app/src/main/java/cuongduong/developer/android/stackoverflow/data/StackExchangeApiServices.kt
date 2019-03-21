package cuongduong.developer.android.stackoverflow.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import cuongduong.developer.android.stackoverflow.data.response.UserListResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.stackexchange.com/2.2/"

interface StackExchangeApiServices {

    // https://api.stackexchange.com/2.2/me?order=desc&sort=reputation&site=stackoverflow - get current user
    // https://api.stackexchange.com/2.2/users/{ids}?order=desc&sort=reputation&site=stackoverflow

    @GET("users/{ids}?order=desc&sort=reputation&site=stackoverflow")
    fun getUser(
        @Path("ids") id: String
    ): Deferred<UserListResponse>

    companion object {
        operator fun invoke(): StackExchangeApiServices {
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