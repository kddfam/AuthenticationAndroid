package com.kdd.authentication.xtrs

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseFragment : Fragment(), CoroutineScope {

    // Job variable declaration
    private lateinit var mJob : Job

    // Coroutine definition
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    // onCreate function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mJob = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }

}