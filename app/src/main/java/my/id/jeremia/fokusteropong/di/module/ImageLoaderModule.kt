package my.id.jeremia.fokusteropong.di.module


import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.id.jeremia.fokusteropong.network.Networking
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageLoaderModule {

    @Provides
    @Singleton
    fun provideNetworkImageLoader(
        @ApplicationContext ctx: Context,
    ) : ImageLoader = ImageLoader.Builder(ctx)
        .okHttpClient(
            Networking.createOkHttpClient(
                ctx.cacheDir,
                10,
            )
        )
        .build()

}