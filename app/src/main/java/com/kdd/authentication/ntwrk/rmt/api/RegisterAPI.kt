package com.kdd.authentication.ntwrk.rmt.api

import com.kdd.authentication.ntwrk.lcl.ety.UserEntity
import com.kdd.authentication.ntwrk.rmt.rspns.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterAPI {

    @FormUrlEncoded
    @POST("register")

    suspend fun register(
        @Field("firstname") firstname : String?,
        @Field("lastname") lastname : String?,
        @Field("email") email : String?,
        @Field("phonenumber") phonenumber : Long?,
        @Field("password") password : String?
    ) : Response<UserEntity>

    companion object {

        private val apiAddress : String? = "http://192.168.43.32:1234/api/"

        operator fun invoke() : RegisterAPI {
            return Retrofit.Builder().baseUrl(apiAddress!!).addConverterFactory(GsonConverterFactory.create()).build().create(RegisterAPI::class.java)
        }

    }
}