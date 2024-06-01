package my.id.jeremia.fokusteropong.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.id.jeremia.fokusteropong.BuildConfig
import my.id.jeremia.fokusteropong.di.qualifier.BaseUrl
import my.id.jeremia.fokusteropong.network.Networking
import my.id.jeremia.fokusteropong.network.apis.MachineAPI
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideApiOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient = Networking.createOkHttpClient(
        context.cacheDir,
        100 * 1024 * 1024,
    )

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = "http://"+BuildConfig.SERVER_IPADDR+":"+BuildConfig.SERVER_PORT+"/"

    @Provides
    @Singleton
    fun provideMachineAPI(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient
    ): MachineAPI = Networking.createService(
        baseUrl,
        okHttpClient,
        MachineAPI::class.java
    )

}