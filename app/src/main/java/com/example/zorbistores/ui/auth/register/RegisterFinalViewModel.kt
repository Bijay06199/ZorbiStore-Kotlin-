package com.example.zorbistores.ui.auth.register

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.example.zorbistores.base.BaseViewModel
import com.example.zorbistores.data.repo.RegisterRepository
import com.example.zorbistores.ui.main.MainActivity
import com.example.zorbistores.utils.ApiException
import com.example.zorbistores.utils.AuthListenerInfo
import com.example.zorbistores.utils.NoInternetException
import com.example.zorbistores.utils.SingleLiveEvent
import com.example.zorbistores.utils.extentions.isValidEmail
import kotlinx.coroutines.launch

class RegisterFinalViewModel(private val registerRepository: RegisterRepository) :BaseViewModel() {

    val backClickedEvent = SingleLiveEvent<Unit>()
    var userName: String? = null
    var email: String? = null
    var address: String? = null
    var authListenerInfo: AuthListenerInfo? = null

    fun back(view: View) {
        backClickedEvent.call()
    }

    fun success(view: View) {
        if (userName.isNullOrEmpty()) {

            authListenerInfo?.onInfo("Please enter number")

        } else if (email.isNullOrEmpty()) {
            authListenerInfo?.onInfo("Please enter email")
        } else if (userName!!.length < 10) {
            authListenerInfo?.onWarning("Please enter valid number")
        } else if(address.isNullOrEmpty()){
            authListenerInfo?.onWarning("Please enter your address")
        }

        else if (email!!.isValidEmail()) {

            viewModelScope.launch {

                try {
                    val registerResponse = registerRepository.register(email!!, userName!!,address!!)

                    if (registerResponse.isSuccessful) {
                        var customerId=registerResponse.body()?.id
                        registerRepository.preferenceManager.setCustomerId(customerId!!)
                        registerRepository.preferenceManager.setEmail(email)
                        registerRepository.preferenceManager.setNumber(userName)
                        registerRepository.preferenceManager.setAddress(address)
                        registerRepository.preferenceManager.setIsLoggedIn(true)
                        Intent(view.context, MainActivity::class.java).also {
                            view.context.startActivity(it)
                            Toast.makeText(view.context,"Your customer id is"+customerId,Toast.LENGTH_LONG).show()
                            authListenerInfo?.onSuccess("Registered successful")
                        }
                    }
                    else{
                        authListenerInfo?.onInfo(registerResponse.message())
                    }
                } catch (e: ApiException) {
                    authListenerInfo?.onWarning(e.message!!)
                } catch (e: NoInternetException) {
                    authListenerInfo?.onInfo(e.message!!)


                }


            }
        }
        else{
            authListenerInfo?.onWarning("Please enter valid email")
        }
    }
}

