package com.esa.reqresuser.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.esa.reqresuser.data.PagingSource
import com.esa.reqresuser.data.network.LoginRequest
import com.esa.reqresuser.data.network.api.ApiService
import com.esa.reqresuser.data.network.response.DataUser
import com.esa.reqresuser.data.network.response.LoginResponse

class UserRepository(private val apiService: ApiService) {
    fun getUsers(): LiveData<PagingData<DataUser>> {
        return Pager (
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                PagingSource(apiService)
            }
        ).liveData
    }

    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            Result.success(response)
        } catch (e: Exception) {
            // Handle exceptions, you might want to handle different types of exceptions based on your needs
            Result.failure(e)
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}