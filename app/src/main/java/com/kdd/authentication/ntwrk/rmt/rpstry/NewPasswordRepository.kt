package com.kdd.authentication.ntwrk.rmt.rpstry

import com.kdd.authentication.ntwrk.rmt.api.NewPasswordAPI
import com.kdd.authentication.ntwrk.rmt.rspns.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit

class NewPasswordRepository {

    suspend fun onNewPassword(id : Int?, password : String?) : Response<AuthResponse> {
        return NewPasswordAPI().newPassword(id, password)
    }
}