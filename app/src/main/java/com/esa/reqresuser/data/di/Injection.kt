package com.esa.reqresuser.data.di

import android.content.Context
import com.esa.reqresuser.data.network.api.ApiConfig
import com.esa.reqresuser.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiClient()
        return UserRepository.getInstance(apiService)
    }
}