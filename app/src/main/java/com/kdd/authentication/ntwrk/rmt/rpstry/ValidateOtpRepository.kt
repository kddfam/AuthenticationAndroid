package com.kdd.authentication.ntwrk.rmt.rpstry

import com.kdd.authentication.ntwrk.rmt.api.ForgotPasswordAPI
import com.kdd.authentication.ntwrk.rmt.api.ValidateOtpAPI
import com.kdd.authentication.ntwrk.rmt.rspns.AuthResponse
import com.kdd.authentication.ntwrk.rmt.rspns.OtpResponse
import retrofit2.Response

class ValidateOtpRepository {

    suspend fun onValidateOtp(otp : Int?) : Response<AuthResponse> {
        return ValidateOtpAPI().validateOtp(otp)
    }

}