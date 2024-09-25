package com.intsab.code_data.di

import android.app.Application
import com.intsab.code_data.repo.WhetherRepo
import com.intsab.code_data.services.WhetherService
import com.intsab.common.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideCacheDir(application: Application): File {
        return File(application.cacheDir, "http_cache") // Provide your cache directory
    }
    @Provides
    @Singleton
    fun provideCache(cacheDir: File): Cache {
        val cacheSize = 10 * 1024 * 1024L // 10 MB
        return Cache(cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val cacheInterceptor = Interceptor { chain ->
            var request = chain.request()
            val response: Response = chain.proceed(request)

            if (request.method().equals("GET", ignoreCase = true)) {
                val maxAge = 60 * 60 // Cache responses for 1 hour (60 minutes * 60 seconds)
                response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .build()
            } else {
                response
            }
        }

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(cacheInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideWhetherService(okHttpClient: OkHttpClient): WhetherService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient) // Attach OkHttpClient with caching to Retrofit
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WhetherService::class.java)
    }

    @Provides
    @Singleton
    fun provideWhetherRepo(whetherService: WhetherService): WhetherRepo {
        return WhetherRepo(whetherService)
    }
}
