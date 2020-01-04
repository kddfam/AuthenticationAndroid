package com.kdd.authentication.ntwrk.rmt.rpstry

import com.kdd.authentication.ntwrk.rmt.api.ForgotPasswordAPI
import com.kdd.authentication.ntwrk.rmt.rspns.OtpResponse
import retrofit2.Response

class ForgotPasswordRepository {

    suspend fun onForgotPassword(email : String?) : Response<OtpResponse> {
        return ForgotPasswordAPI().forgotPassword(email)
    }

}