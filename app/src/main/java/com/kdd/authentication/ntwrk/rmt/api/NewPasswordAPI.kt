package com.kdd.authentication.ntwrk.rmt.api

import com.kdd.authentication.ntwrk.rmt.rspns.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NewPasswordAPI {
    @FormUrlEncoded
    @POST("np")

    suspend fun newPassword(
        @Field("id") id : Int?,
        @Field("password") password : String?
    ) : Response<AuthResponse>

    companion object {

        private val apiAddress : String? = "http://192.168.43.32:1234/api/"

        operator fun invoke() : NewPasswordAPI {
            return Retrofit.Builder().baseUrl(apiAddress!!).addConverterFactory(GsonConverterFactory.create()).build().create(NewPasswordAPI::class.java)
        }
    }
}