package com.kdd.authentication.ntwrk.rmt.rpstry

import com.kdd.authentication.ntwrk.rmt.api.LoginAPI
import com.kdd.authentication.ntwrk.rmt.rspns.AuthResponse
import retrofit2.Response
import java.math.BigInteger

class LoginRepository {

    suspend fun onLogin(phonenumber : Long?, password : String?) : Response<AuthResponse> {
        return LoginAPI().login(phonenumber, password)
    }

}