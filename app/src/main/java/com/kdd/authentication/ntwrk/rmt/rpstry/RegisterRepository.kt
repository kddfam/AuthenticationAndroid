package com.kdd.authentication.ntwrk.rmt.rpstry

import com.kdd.authentication.ntwrk.lcl.ety.UserEntity
import com.kdd.authentication.ntwrk.rmt.api.RegisterAPI
import retrofit2.Response

class RegisterRepository {

    suspend fun onRegister(firstname : String?, lastname : String?, email : String?, phonenumber : Long?, password : String?) : Response<UserEntity> {
        return RegisterAPI().register(firstname, lastname, email, phonenumber, password)
    }

}