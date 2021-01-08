package com.example.zorbistores.ui.main.cart.checkout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.andrognito.flashbar.Flashbar
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseActivity
import com.example.zorbistores.databinding.ActivityCheckOutBinding
import com.example.zorbistores.ui.main.MainActivity
import com.example.zorbistores.ui.main.cart.ShoppingCart
import com.example.zorbistores.utils.AuthListenerInfo
import com.example.zorbistores.utils.extentions.*
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_check_out.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckOutActivity : BaseActivity<ActivityCheckOutBinding,CheckOutViewModel>(),AuthListenerInfo {
    override fun getLayoutId(): Int =R.layout.activity_check_out
    override fun getViewModel(): CheckOutViewModel =checkOutViewModel
    private val checkOutViewModel:CheckOutViewModel by viewModel()


    var flashbar:Flashbar?=null
    var totalPrice:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         totalPrice=intent.getStringExtra("totalPrice")
         initView()
    }

    private fun initView() {

        with(viewDataBinding){
            eTFirstName.setText(preferenceManager.getFirstName())
            eTLastName.setText(preferenceManager.getLastName())
            eTMailAddress.setText(preferenceManager.getEmail())
            eTContactNumber.setText(preferenceManager.getNumber())
            eTAddress1.setText(preferenceManager.getAddress())
            eTAddress2.setText(preferenceManager.getAddress())

            tVItemCount.setText(ShoppingCart.getCart().size.toString())




            var totalPrice = ShoppingCart.getCart()


                .fold(0.toDouble()) { acc, cartItemModel ->
                    if(cartItemModel.product.price==""){
                        return
                    }else{
                        acc + cartItemModel.quantity.times(
                            cartItemModel.product.price!!.toDouble()
                        )
                    }

                }
            tVTotalAmt.text="RS ${totalPrice}"


        }





        checkOutViewModel.authListenerInfo=this

    }


    override fun onSuccess(message: String) {

        flashbar=successFlashBar(message)
        flashbar?.show()

    }

    override fun onStarted() {


    }

    override fun onInfo(message: String) {
        flashbar=infoFlashBar(message)
        flashbar?.show()

    }

    override fun onWarning(message: String) {
        flashbar=warningFlashBar(message)
        flashbar?.show()

    }

    override fun onDanger(message: String) {
        flashbar=dangerFlashBar(message)
        flashbar?.show()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}