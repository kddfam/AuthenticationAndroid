package com.kdd.authentication.ui.fgmt


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kdd.authentication.R
import com.kdd.authentication.ntwrk.lcl.dbcls.AuthDatabase
import com.kdd.authentication.xtrs.BaseFragment
import kotlinx.coroutines.launch

class Main : BaseFragment() {

    lateinit var mFirstname : TextView
    lateinit var mLastname : TextView
    lateinit var mEmail : TextView
    lateinit var mPhoneNumber : TextView
    lateinit var mPassword : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launch {
            context?.let {

                val user = AuthDatabase(it).getDao().listUser()
                mFirstname = view!!.findViewById(R.id.firstname)
                mLastname = view!!.findViewById(R.id.lastname)
                mEmail = view!!.findViewById(R.id.email)
                mPhoneNumber = view!!.findViewById(R.id.phonenumber)
                mPassword = view!!.findViewById(R.id.password)

                mFirstname.text = user.get(0).firstname
                mLastname.text = user.get(0).lastname
                mEmail.text = user.get(0).email
                mPhoneNumber.text = user.get(0).phonenumber.toString()
                mPassword.text = user.get(0).password

            }
        }
    }


}
