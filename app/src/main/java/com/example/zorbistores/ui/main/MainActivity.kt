package com.example.zorbistores.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.zorbistores.BuildConfig
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseActivity
import com.example.zorbistores.databinding.ActivityMainBinding
import com.example.zorbistores.ui.auth.login.LoginFragment
import com.example.zorbistores.ui.main.cart.CartFragment
import com.example.zorbistores.ui.main.cart.ShoppingCart
import com.example.zorbistores.ui.main.cart.model.CartItemModel
import com.example.zorbistores.ui.main.categories.CategoriesFragment
import com.example.zorbistores.ui.main.home.HomeFragment
import com.example.zorbistores.ui.main.home.adapter.LatestProductAdapter
import com.example.zorbistores.ui.main.shop.ShopFragment
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import com.google.android.gms.common.internal.Constants
import com.google.android.material.badge.BadgeDrawable
import io.paperdb.Paper
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : BaseActivity<ActivityMainBinding,MainViewModel>(),LatestProductAdapter.OnItemClickListener {

    override fun getLayoutId(): Int =R.layout.activity_main
    override fun getViewModel(): MainViewModel =mainViewModel
    private  val mainViewModel:MainViewModel by viewModel()




   lateinit var badge:BadgeDrawable
    lateinit var latestProductAdapter: LatestProductAdapter
    var itemList=ArrayList<NewProductResponseItem>()
    var itemModel=ArrayList<CartItemModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        badge=viewDataBinding.bottomNavigation.getOrCreateBadge(R.id.page_3)
        badge.isVisible=true
       badge.number=ShoppingCart.getCart().size



        initView()

        bottomNavigationItem()

        if(savedInstanceState==null){

                HomeFragment.start(this@MainActivity,R.id.main_screen_container)

        }

    }




    private fun initView() {
        with(viewDataBinding){


            latestProductAdapter= LatestProductAdapter(this@MainActivity,this@MainActivity,itemList)
            fabButton.setOnClickListener {
                ShopFragment.start(this@MainActivity,R.id.main_screen_container)
            }
        }
    }

    fun isOnline(): Boolean {
        val cm =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null &&
                cm.activeNetworkInfo!!.isConnectedOrConnecting
    }

    private fun bottomNavigationItem() {
        with(viewDataBinding){

            bottomNavigation.setOnNavigationItemSelectedListener { item ->

                when(item.itemId){
                    R.id.page_1->{
                        HomeFragment.start(this@MainActivity,R.id.main_screen_container)
                        true
                    }
                    R.id.page_2->{
                        CategoriesFragment.start(this@MainActivity,R.id.main_screen_container)
                        true
                    }
                    R.id.page_3->{
                        CartFragment.start(this@MainActivity,R.id.main_screen_container)
                        true
                    }


                    R.id.page_4->{
                        LoginFragment.start(this@MainActivity,R.id.main_screen_container)
                        true
                    }
                    else -> false
                }

            }


        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (BuildConfig.DEBUG && data == null) {
                error("Assertion failed")

            }



        }


    }




    override fun onItemLatestSelect(position: Int, itemList: NewProductResponseItem) {
        badge.number=ShoppingCart.getCart().size
    }

    override fun onLayoutAddLatestClick(position: Int, itemList: NewProductResponseItem) {

    }


}