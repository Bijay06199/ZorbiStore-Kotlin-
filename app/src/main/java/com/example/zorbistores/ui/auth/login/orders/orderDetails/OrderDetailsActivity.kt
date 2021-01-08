package com.example.zorbistores.ui.auth.login.orders.orderDetails

import android.os.Bundle
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseActivity
import com.example.zorbistores.constants.Constants
import com.example.zorbistores.databinding.ActivityOrderDetailsBinding
import com.example.zorbistores.ui.auth.login.orders.orderDetails.adapter.OrderDetailsAdapter
import com.example.zorbistores.ui.auth.login.orders.orderDetails.model.OrderDetailsModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderDetailsActivity : BaseActivity<ActivityOrderDetailsBinding,OrderDetailsViewModel>() {

    override fun getLayoutId(): Int =R.layout.activity_order_details
    override fun getViewModel(): OrderDetailsViewModel =orderDetailsViewModel
     private val orderDetailsViewModel:OrderDetailsViewModel by viewModel()

    var productName:String?=null
    var quantityT:Int?=null
    var rateT:String?=null
    var order:String?=null

    lateinit var orderDetailsAdapter: OrderDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productName=intent.getStringExtra(Constants.Product)
        quantityT=intent.getIntExtra(Constants.Quantity,0)
        rateT=intent.getStringExtra(Constants.Price)
        order=intent.getStringExtra("order")
        initView()

    }

    private fun initView() {
        with(viewDataBinding){

            ivBack.setOnClickListener {
                finish()
            }

            var total=  quantityT?.times(rateT!!.toDouble())
            var itemList=ArrayList<OrderDetailsModel>()
            orderDetailsAdapter=OrderDetailsAdapter(itemList)
            recyclerviewOrderDetails.adapter=orderDetailsAdapter

            for (i in 0..itemList.size){
                itemList.add(OrderDetailsModel(productName!!,quantityT!!,rateT!!,total))

            }
             orderDetailsAdapter.addAll(itemList)




//            product.setText(productName)
//            quantity.setText(quantityT.toString())
//            rate.setText(rateT)
           orderNo.setText(order)
           tvTotalPrice.setText(rateT)
//            quantityRate.setText(total.toString())


        }
    }
}