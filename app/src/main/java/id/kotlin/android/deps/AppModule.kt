package id.kotlin.android.deps

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import id.kotlin.android.App
import id.kotlin.android.data.NetworkService
import id.kotlin.android.data.NetworkServices
import id.kotlin.android.ext.Configs
import id.kotlin.android.ext.clazz
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
class AppModule {

    @Provides
    @Singleton
    fun providesNetworkService(okHttpClient: OkHttpClient): NetworkService = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Configs.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(clazz<NetworkService>())

    @Provides
    @Singleton
    fun providesNetworkServices(networkService: NetworkService) = NetworkServices(networkService)

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache,
                             connectionPool: ConnectionPool,
                             interceptor: Interceptor,
                             httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectionPool(connectionPool)
            .build()

    @Provides
    @Singleton
    fun providesContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesCache(context: Context): Cache = Cache(
            context.externalCacheDir,
            Configs.CACHE_SIZE.toLong()
    )

    @Provides
    @Singleton
    fun providesConnectionPool(): ConnectionPool = ConnectionPool(
            Configs.MAX_IDLE_CONNECTIONS,
            Configs.KEEP_ALIVE_DURATION.toLong(),
            TimeUnit.SECONDS
    )

    @Provides
    @Singleton
    fun providesInterceptor(): Interceptor = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder().addHeader("Content-Type", "application/json").build())
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(BODY)
}