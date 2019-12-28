package ru.wilddisk.retrofitcoroutine.network.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.wilddisk.retrofitcoroutine.BuildConfig
import ru.wilddisk.retrofitcoroutine.model.Comment
import ru.wilddisk.retrofitcoroutine.model.Post
import ru.wilddisk.retrofitcoroutine.model.User

/**
 * Take data out of server
 */
interface JsonApi {
    @GET("/posts")
    fun getPostsAsync(): Deferred<Response<List<Post>>>

    @GET("/users")
    fun getUsersAsync(): Deferred<Response<List<User>>>

    @GET("/users/{id}")
    fun getUserAsync(@Path("id") id: Long): Deferred<User>

    @GET("/comments")
    fun getCommentsAsync(): Deferred<Response<List<Comment>>>

    /**
     * Factory for build connection with server and take data
     */
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
        fun apiFactory(): JsonApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverterFactory(MoshiConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(getClient())
                .build()
                .create(JsonApi::class.java)
        }

        /**
         * Logging data acquisition status
         */
        private fun getClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }
            return client.build()
        }
    }
}