package com.example.zorbistores.ui.main.cart

import android.content.Intent
import android.view.View
import com.example.zorbistores.base.BaseViewModel
import com.example.zorbistores.ui.main.cart.checkout.CheckOutActivity
import com.example.zorbistores.utils.AuthListenerInfo

class CartViewModel :BaseViewModel(){

    var authListenerInfo:AuthListenerInfo?=null


//    fun checkOUt(view: View){
//        Intent(view.context,CheckOutActivity::class.java).also {
//            view.context.startActivity(it)
//        }
//    }
}