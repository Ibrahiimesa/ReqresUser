package com.esa.reqresuser.data.network.api

import com.esa.reqresuser.data.network.LoginRequest
import com.esa.reqresuser.data.network.response.LoginResponse
import com.esa.reqresuser.data.network.response.ResponseData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int?,
    ): ResponseData

    @POST("api/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}