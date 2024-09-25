package com.intsab.code_data.di

import com.intsab.code_data.repo.WhetherRepo
import com.intsab.code_data.services.WhetherService
import com.intsab.common.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by intsabhaider
 * on 25,September,2024
 */

    @Module
    class NetworkModule {
        @Provides
        fun provideWhetherService(): WhetherService {
            // Assuming you are using Retrofit to create this service
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WhetherService::class.java)
        }

        @Provides
        fun provideWhetherRepo(whetherService: WhetherService): WhetherRepo {
            return WhetherRepo(whetherService)
        }
    }
