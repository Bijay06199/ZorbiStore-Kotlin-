package com.example.zorbistores.ui.auth.login.orders

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseActivity
import com.example.zorbistores.constants.Constants
import com.example.zorbistores.databinding.ActivityCustomerOrderBinding
import com.example.zorbistores.ui.auth.login.orders.orderDetails.OrderDetailsActivity
import com.example.zorbistores.ui.auth.login.response.CustomerOrderResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomerOrderActivity : BaseActivity<ActivityCustomerOrderBinding,CustomerOrderViewModel>(),CustomerOrderAdapter.OnItemClickListener {

    override fun getLayoutId(): Int =R.layout.activity_customer_order
    override fun getViewModel(): CustomerOrderViewModel=customeOrderViewModel
    private val customeOrderViewModel:CustomerOrderViewModel by viewModel()


   lateinit var customerOrderAdapter: CustomerOrderAdapter
    var itemList = ArrayList<CustomerOrderResponse>()
    var product:String?=null
    var quantity:Int?=null
    var rate:String?=null
    var order:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        with(viewDataBinding){
            with(customeOrderViewModel){

                customerOrderAdapter= CustomerOrderAdapter(this@CustomerOrderActivity,itemList)
                rvCustomerOrders.adapter=customerOrderAdapter

                getOrders(preferenceManager.getCustomerId())
                orderEvent.observe(this@CustomerOrderActivity, Observer {

                    itemList.addAll(orders!!)
                    customerOrderAdapter.notifyDataSetChanged()
                    progressBar6.visibility= View.GONE

                })

            }
        }
    }

    private fun initView() {
        with(viewDataBinding){

            ivBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun onItemSelect(position: Int, itemList: CustomerOrderResponse) {

        var lineItems=itemList.lineItems

        for (i in 0 until lineItems!!.size){
             product= itemList.lineItems?.get(i)?.name
             quantity=itemList.lineItems?.get(i)?.quantity
             rate=itemList.lineItems?.get(i)?.total
             order=itemList.number


            var intent=Intent(this,OrderDetailsActivity::class.java)
            intent.putExtra(Constants.Product,product)
            intent.putExtra(Constants.Quantity,quantity)
            intent.putExtra(Constants.Price,rate)
            intent.putExtra("order",order)
            startActivity(intent)


        }





    }
}