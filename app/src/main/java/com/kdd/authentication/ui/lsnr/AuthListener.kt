package com.kdd.authentication.ui.lsnr

interface AuthListener {

    fun onSuccess(message : String?)

    fun onFailed(message : String?)

}