package com.example.zorbistores.ui.auth.login

import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.zorbistores.base.BaseViewModel
import com.example.zorbistores.data.prefs.PreferenceManager
import com.example.zorbistores.data.repo.CustomerDetailsRepository
import com.example.zorbistores.data.repo.CustomerRepository
import com.example.zorbistores.ui.auth.login.response.CustomerResponse
import com.example.zorbistores.utils.ApiException
import com.example.zorbistores.utils.AuthListenerInfo
import com.example.zorbistores.utils.NoInternetException
import com.example.zorbistores.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel(private val preferenceManager: PreferenceManager,private val customerDetailsRepository: CustomerDetailsRepository,private val customerRepository: CustomerRepository) :BaseViewModel(){
    var email:String?=null
    var loginSuccessEvent=SingleLiveEvent<Unit>()
    var password:String?=null
    var authListenerInfo: AuthListenerInfo?=null

    fun login(view: View){


    }

    var customerDetailEvent=SingleLiveEvent<Unit>()
    var customerEvent=SingleLiveEvent<Unit>()
    var customer:List<CustomerResponse>?=null


    fun getCustomers(email:String?){

        viewModelScope.launch {
            try {
                val response= customerDetailsRepository.getCustomer(email)
                customer=response.body()
                customerDetailEvent.call()
            }
            catch (e: NoInternetException){

            }catch (e:ApiException){

            }

        }
    }


    fun getCustomers(){

        if (email.isNullOrEmpty()){
            authListenerInfo?.onDanger("Please enter email")

        }
        else if (password.isNullOrEmpty()){
            authListenerInfo?.onDanger("Please enter password")
        }
        else{

            viewModelScope.launch {
                authListenerInfo?.onStarted()

                try {
                    val response= customerRepository.getCustomer()
                    customer=response.body()
                    loginSuccessEvent.call()
                }
                catch (e: NoInternetException){

                }catch (e:ApiException){

                }

            }




        }


    }

//    fun editProfile(view: View){
//
//        Intent(view.context,EditprofileActivity::class.java).also {
//            view.context.startActivity(it)
//        }
//    }
}