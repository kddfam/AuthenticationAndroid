package com.kdd.authentication.ui.fgmt


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.kdd.authentication.R
import com.kdd.authentication.databinding.FragmentForgotPasswordBinding
import com.kdd.authentication.ntwrk.lcl.dbcls.AuthDatabase
import com.kdd.authentication.ui.lsnr.AuthListener
import com.kdd.authentication.ui.vwmdl.AuthViewModel
import com.kdd.authentication.xtrs.BaseFragment
import kotlinx.coroutines.launch
import java.lang.Exception

class ForgotPassword : BaseFragment(), AuthListener {

    lateinit var mEmail : EditText
    lateinit var mSnackbar: Snackbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentForgotPasswordBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_forgot_password, container, false)
        val viewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.fpViewModel = viewmodel
        binding.lifecycleOwner = this
        viewmodel.authListener = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mEmail = view!!.findViewById(R.id.email)

        launch {
            context?.let {

                try {
                    val user = AuthDatabase(it).getDao().listUser()
                    val email = user.get(0).email
                    mEmail.setText(email)
                }
                catch (e : Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onSuccess(message: String?) {
        mSnackbar = Snackbar.make(view!!, message.toString(), Snackbar.LENGTH_LONG)
        mSnackbar.show()

        val action = ForgotPasswordDirections.forgotPasswordToVerifyOtp()
        Navigation.findNavController(view!!).navigate(action)
    }

    override fun onFailed(message: String?) {
        mSnackbar = Snackbar.make(view!!, message.toString(), Snackbar.LENGTH_LONG)
        mSnackbar.show()
    }


}
