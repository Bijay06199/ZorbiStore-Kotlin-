package com.example.zorbistores.ui.main.home.onSale

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.lifecycle.Observer
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseActivity
import com.example.zorbistores.databinding.ActivityOnSaleBinding
import com.example.zorbistores.ui.main.ImageActivity
import com.example.zorbistores.ui.main.MainActivity
import com.example.zorbistores.ui.main.cart.ShoppingCart
import com.example.zorbistores.ui.main.cart.model.CartItemModel
import com.example.zorbistores.ui.main.home.adapter.MultiImageAdapter
import com.example.zorbistores.ui.main.home.adapter.OnSaleProductAdapter
import com.example.zorbistores.ui.main.home.onSale.adapter.OnInnerSaleAdapter
import com.example.zorbistores.ui.main.shop.response.Image
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import com.example.zorbistores.utils.bindings.BindingUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.persistent_bottom_sheet.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnSaleActivity : BaseActivity<ActivityOnSaleBinding,OnSaleViewModel>(),OnInnerSaleAdapter.OnItemClickListener,
MultiImageAdapter.OnItemClickListener{

    override fun getLayoutId(): Int =R.layout.activity_on_sale
    override fun getViewModel(): OnSaleViewModel =onSaleViewModel
    private val onSaleViewModel:OnSaleViewModel by viewModel()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<MaterialCardView>
    lateinit var onInnerSaleAdapter: OnInnerSaleAdapter
    lateinit var multiImageAdapter:MultiImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setUpRecyclerView()
        bottomSheetBehavior= BottomSheetBehavior.from<MaterialCardView>(persistent_bottom_sheet)

        if (savedInstanceState==null){
            bottomSheetBehavior.state= BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){


                    BottomSheetBehavior.STATE_EXPANDED->{
                        viewDataBinding.cvWithBottomSheet.visibility= View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_HIDDEN->{
                        viewDataBinding.cvWithBottomSheet.visibility= View.GONE
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED.and(20)->{
                        viewDataBinding.cvWithBottomSheet.visibility= View.VISIBLE
                    }

                }
            }

        })

    }

    private fun setUpRecyclerView() {
        with(viewDataBinding){
            with(onSaleViewModel){

                var productList = ArrayList<NewProductResponseItem>()

                onInnerSaleAdapter = OnInnerSaleAdapter(this@OnSaleActivity, this@OnSaleActivity,productList)
                getOnSaleProduct()
                onSaleProductEvent.observe(this@OnSaleActivity, Observer {
                    recyclerViewAll.adapter = onInnerSaleAdapter
                    productList.addAll(onSale!!)
                    onInnerSaleAdapter.notifyDataSetChanged()
                    progressBar4.visibility=View.GONE

                })
            }
        }
    }

    private fun initView() {
        with(viewDataBinding){

            var animation= AnimationUtils.loadAnimation(this@OnSaleActivity,R.anim.rotation_anim)
            animation.setInterpolator ( LinearInterpolator() )
            progressBar4.startAnimation(animation)

            ivBack.setOnClickListener {
                startActivity(Intent(this@OnSaleActivity, MainActivity::class.java))

            }

        }
    }

    override fun onItemSaleSelect(position: Int, itemList: NewProductResponseItem) {


        with(viewDataBinding){

            for (i in 0..position){





                var cartItemModel = CartItemModel(itemList, 0)

                var _counter = cartItemModel.quantity



                btnAddToCart.setOnClickListener {

                    _counter++

                    val item = CartItemModel(itemList)
                    ShoppingCart.addItem(item,this@OnSaleActivity)
                    btnAddToCart.visibility = View.GONE
                    addSubtractButton.visibility = View.VISIBLE
                    tvCartQuantity.setText(Integer.toString(_counter))
                }


                clAdd.setOnClickListener { view ->

                    _counter++
                    val item = CartItemModel(itemList)
                    ShoppingCart.addItem(item,this@OnSaleActivity)

                    ShoppingCart.getCart()
                    tvCartQuantity.setText(Integer.toString(_counter))


                }


                clSubtract.setOnClickListener {

                    _counter--
                    val item = CartItemModel(itemList)
                    ShoppingCart.removeItem(item, this@OnSaleActivity)
                    ShoppingCart.getCart()
                    tvCartQuantity.setText(Integer.toString(_counter))

                    if (_counter == 0) {
                        ShoppingCart.removeItem(item, this@OnSaleActivity)
                        btnAddToCart.visibility = View.VISIBLE
                        addSubtractButton.visibility = View.GONE

                    }
                }

            }


            var images = itemList.images
            multiImageAdapter= MultiImageAdapter(this@OnSaleActivity,
                images as List<Image>
            )
            viewDataBinding.rvMultiImage.adapter=multiImageAdapter

            if (images?.size!! >1){


                viewDataBinding.viewPagerIndicatorIconsLayout.visibility=View.VISIBLE


            }
            else{
                viewDataBinding.viewPagerIndicatorIconsLayout.visibility=View.GONE

            }



            var productName = itemList.name
            var description = itemList.description
            if (description==""){
                viewDataBinding.textView20.setText("")
                viewDataBinding.tvDescriptions.setText(Html.fromHtml(description))
            }
            else{
                viewDataBinding.textView20.setText("Product Descriptions")
                viewDataBinding.tvDescriptions.setText(Html.fromHtml(description))
            }

            var price = itemList.price

            var regularPrice=itemList.regularPrice
            var saleprice=itemList.salePrice

            if (saleprice==""){
                viewDataBinding.tvRegularPrice.setText("")
            }
            else{
                viewDataBinding.tvRegularPrice.setText(regularPrice)
            }

            viewDataBinding.tvProductName1.setText(productName)
            viewDataBinding.tvProductName.setText(productName)
            viewDataBinding.tvPrice.setText(price)
            viewDataBinding.tvTotalAmount.setText(price)


            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED.and(20)
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }

        }


        viewDataBinding.btnAddToCart.visibility = View.VISIBLE
        viewDataBinding.addSubtractButton.visibility = View.GONE


    }

    override fun onSaleLayoutAddClick(position: Int, itemList: NewProductResponseItem) {

    }


    override fun onImageSelect(position: Int, itemList: Image) {

        var intent= Intent(
            this,
            ImageActivity::class.java
        )
        intent.putExtra("images",itemList.src)
        startActivity(intent)
    }
}
