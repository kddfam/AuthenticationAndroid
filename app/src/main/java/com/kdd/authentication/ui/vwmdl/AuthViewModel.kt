package com.kdd.authentication.ui.vwmdl

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.kdd.authentication.ntwrk.lcl.dbcls.AuthDatabase
import com.kdd.authentication.ntwrk.lcl.ety.UserEntity
import com.kdd.authentication.ntwrk.rmt.rpstry.ForgotPasswordRepository
import com.kdd.authentication.ntwrk.rmt.rpstry.LoginRepository
import com.kdd.authentication.ntwrk.rmt.rpstry.RegisterRepository
import com.kdd.authentication.ui.lsnr.AuthListener
import com.kdd.authentication.xtrs.Coroutine
import java.math.BigInteger

class AuthViewModel(application : Application) : AndroidViewModel(application) {

    var firstname : String? = null
    var lastname : String? = null
    var email : String? = null
    var phonenumber : Long? = 0
    var password : String? = null
    var authListener : AuthListener? = null

    //
    lateinit var mFirstName : String
    lateinit var mLastName : String
    lateinit var mEmail : String
    lateinit var mPassword : String

    private val mContext : Context = getApplication<Application>().applicationContext

    fun onLogin(view : View) {

        Coroutine.main {

            val response =  LoginRepository().onLogin(phonenumber, password)
            if (response.isSuccessful) {
                authListener?.onSuccess(response.body()?.message)
            }
            else {
                authListener?.onFailed("Invalid Credentials")
//                Log.e("Error", response.errorBody()?.string()!!)
            }

        }

    }

    fun onRegister(view : View) {

        Coroutine.main {

            val response_register = RegisterRepository().onRegister(firstname, lastname, email, phonenumber, password)
            if(response_register.isSuccessful) {
                authListener?.onSuccess(response_register.body()?.id.toString())

                // For Local Database
                val mUserId : Int = response_register.body()?.id!!
                mFirstName = response_register.body()?.firstname!!
                mLastName = response_register.body()?.lastname!!
                mEmail = response_register.body()?.email!!
                val mPhoneNumber : Long = response_register.body()?.phonenumber!!
                mPassword = response_register.body()?.password!!

                // Adding into local database
                val item = UserEntity(mUserId,mFirstName,mLastName,mEmail,mPhoneNumber,mPassword)
                AuthDatabase(mContext).getDao().addUser(item)

            }
            else {
                authListener?.onFailed(response_register.errorBody()?.string())
            }
        }

    }

    fun onForgotPassword(view: View) {

        Coroutine.main {

            val fp_response = ForgotPasswordRepository().onForgotPassword(email)
            if(fp_response.isSuccessful) {
                authListener?.onSuccess(fp_response.body()?.message.toString())
            }
            else {
                authListener?.onFailed(fp_response.errorBody()?.string())
            }

        }
    }

}