package my.id.jeremia.fokusteropong.network


import com.squareup.moshi.Moshi
import my.id.jeremia.fokusteropong.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {
    private const val NETWORK_CALL_TIMEOUT = 5

    fun<T> createService(baseUrl:String, client:OkHttpClient, service:Class<T>):T{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().build()
                )
            )

            .client(client)
            .build()
            .create(service)
    }

    private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    fun createOkHttpClient(
        cacheDir: File,
        cacheSize: Long,
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .cache(Cache(cacheDir,cacheSize))
            .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }


}