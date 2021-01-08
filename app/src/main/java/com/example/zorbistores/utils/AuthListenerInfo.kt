package com.example.zorbistores.utils

interface AuthListenerInfo {
    fun onSuccess(message:String)

    fun onStarted()

    fun onInfo(message: String)

    fun onWarning(message: String)

    fun onDanger(message: String)

}