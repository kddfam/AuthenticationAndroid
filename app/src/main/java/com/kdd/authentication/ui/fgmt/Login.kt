package com.kdd.authentication.ui.fgmt


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.kdd.authentication.R
import com.kdd.authentication.databinding.FragmentLoginBinding
import com.kdd.authentication.ntwrk.lcl.dbcls.AuthDatabase
import com.kdd.authentication.ui.lsnr.AuthListener
import com.kdd.authentication.ui.vwmdl.AuthViewModel
import com.kdd.authentication.xtrs.BaseFragment
import kotlinx.coroutines.launch

class Login : BaseFragment(), AuthListener {

    lateinit var mSnackbar: Snackbar
    lateinit var mLoginToRegister : TextView
    lateinit var mLoginToForgotPass : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentLoginBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.loginViewModel = viewModel
        viewModel.authListener = this
        binding.lifecycleOwner = this

        mLoginToRegister = binding.loginToRegister
        mLoginToRegister.setOnClickListener { loginToRegisterButtonClick() }

        mLoginToForgotPass = binding.loginToForgotPass
        mLoginToForgotPass.setOnClickListener { loginToForgotPassButtonClick() }
        return binding.root
    }

    private fun loginToForgotPassButtonClick() {
        val actionOne = LoginDirections.loginToForgotPass()
        Navigation.findNavController(view!!).navigate(actionOne)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launch {
            context?.let {
                val user = AuthDatabase(it).getDao().listUser()
                if(user.isNotEmpty()) {
                    val action = LoginDirections.loginToMain()
                    Navigation.findNavController(view!!).navigate(action)
                }
            }
        }
    }

    private fun loginToRegisterButtonClick() {
        val action = LoginDirections.loginToRegister()
        Navigation.findNavController(view!!).navigate(action)
    }

    override fun onSuccess(message: String?) {
        mSnackbar = Snackbar.make(view!!, message.toString(), Snackbar.LENGTH_LONG)
        mSnackbar.show()
    }

    override fun onFailed(message: String?) {
        mSnackbar = Snackbar.make(view!!, message.toString(), Snackbar.LENGTH_LONG)
        mSnackbar.show()
    }


}
