package com.kdd.authentication.ntwrk.rmt.api

import com.kdd.authentication.ntwrk.rmt.rspns.OtpResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ForgotPasswordAPI {
    @FormUrlEncoded
    @POST("fp")

    suspend fun forgotPassword(
        @Field("email") email : String?
    ) : Response<OtpResponse>

    companion object {

        private val apiAddress : String? = "http://192.168.43.32:1234/api/"
        operator fun invoke() : ForgotPasswordAPI {
            return Retrofit.Builder().baseUrl(apiAddress!!).addConverterFactory(GsonConverterFactory.create()).build().create(ForgotPasswordAPI::class.java)
        }
    }
}