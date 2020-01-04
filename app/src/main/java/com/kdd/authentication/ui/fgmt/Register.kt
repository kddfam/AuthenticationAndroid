package com.kdd.authentication.ui.fgmt


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.kdd.authentication.R
import com.kdd.authentication.databinding.FragmentRegisterBinding
import com.kdd.authentication.ntwrk.lcl.ety.UserEntity
import com.kdd.authentication.ui.lsnr.AuthListener
import com.kdd.authentication.ui.vwmdl.AuthViewModel
import com.kdd.authentication.xtrs.BaseFragment
import kotlinx.coroutines.launch

class Register : BaseFragment(), AuthListener {

    lateinit var mSnackbar: Snackbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentRegisterBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_register, container, false)
        val viewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.registerViewModel = viewmodel
        binding.lifecycleOwner = this
        viewmodel.authListener = this
        return binding.root
    }

    override fun onSuccess(message: String?) {
        mSnackbar = Snackbar.make(view!!, message.toString(), Snackbar.LENGTH_LONG)
        mSnackbar.show()

        val action = RegisterDirections.registerToMain()
        Navigation.findNavController(view!!).navigate(action)
    }

    override fun onFailed(message: String?) {
        mSnackbar = Snackbar.make(view!!, message.toString(), Snackbar.LENGTH_LONG)
        mSnackbar.show()
    }


}
