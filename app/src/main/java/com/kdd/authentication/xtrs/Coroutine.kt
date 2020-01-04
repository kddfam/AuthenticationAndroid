package com.kdd.authentication.xtrs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutine {
    fun main(work : suspend(() -> Unit)) = CoroutineScope(Dispatchers.Main).launch {
        work()
    }
}