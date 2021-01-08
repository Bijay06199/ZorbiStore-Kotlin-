package com.example.zorbistores.ui.main.categories.categoriesDetail

import android.os.Bundle
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseActivity
import com.example.zorbistores.constants.Constants
import com.example.zorbistores.databinding.ActivityCategoriesDetailBinding
import com.example.zorbistores.ui.main.MainActivity
import com.example.zorbistores.ui.main.cart.ShoppingCart
import com.example.zorbistores.ui.main.categories.response.CategoriesResponse
import com.example.zorbistores.ui.main.shop.adapter.ProductAdapter
import com.example.zorbistores.ui.main.shop.response.NewProductResponseItem
import com.google.android.material.tabs.TabLayout
import io.paperdb.Paper
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesDetailActivity :
    BaseActivity<ActivityCategoriesDetailBinding, CategoriesDetailViewModel>(),ProductAdapter.OnItemClickListener {

    var tab: Int = 0

    override fun getLayoutId(): Int = R.layout.activity_categories_detail
    override fun getViewModel(): CategoriesDetailViewModel = categoriesDetailViewModel
    private val categoriesDetailViewModel: CategoriesDetailViewModel by viewModel()
    var categoryName: String? = null
    var allCategoryName: String? = null
    var categoryId: Int? = null
    lateinit var productAdapter: ProductAdapter
    var totalCategoriesList: Array<String>? = null
    var categoriesList = ArrayList<CategoriesResponse>()
    var itemList=ArrayList<NewProductResponseItem>()
    var mainActivity:MainActivity?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        productAdapter= ProductAdapter(this,this@CategoriesDetailActivity,itemList)
        categoryName = intent.getStringExtra(Constants.CategoryName)
        categoryId = intent.getIntExtra(Constants.CategoryId, 0)

        if (savedInstanceState == null) {
            CategoriesItemFragment.start(
                this@CategoriesDetailActivity,
                R.id.categories_container,
                categoryId
            )

        }
        initView()
        //  Toast.makeText(this,"Category id is"+categoryId,Toast.LENGTH_LONG).show()
        setUpTabLayout()
        tabListener()


    }

    private fun tabListener() {
        with(viewDataBinding) {
//            viewpagerHeadlines.addOnPageChangeListener(
//                TabLayout.TabLayoutOnPageChangeListener(
//                    tablayoutHeadlines
//                )
//            )

            tablayoutHeadlines.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    //  viewpagerHeadlines.currentItem=tab!!.position
                    when (tab?.position) {
                           0 -> {
                            CategoriesItemFragment.start(
                                this@CategoriesDetailActivity,
                                R.id.categories_container,
                                categoryId)
                              }
                        1->{
                            if (categoryName=="Dori Collection"){
                                CategoriesItemFragment.start(this@CategoriesDetailActivity,
                                    R.id.categories_container,90)
                            }
                            else{
                                CategoriesItemFragment.start(this@CategoriesDetailActivity,
                                    R.id.categories_container,58)
                            }
                        }
                        2->{
                            CategoriesItemFragment.start(this@CategoriesDetailActivity,
                                R.id.categories_container,45)
                        }
                        3->{
                            CategoriesItemFragment.start(this@CategoriesDetailActivity,
                                R.id.categories_container,57)
                        }
                        4->{
                            CategoriesItemFragment.start(this@CategoriesDetailActivity,
                                R.id.categories_container,56)
                        }
                        5->{
                            CategoriesItemFragment.start(this@CategoriesDetailActivity,
                                R.id.categories_container,48)
                        }
                    }
                }


            })

            tablayoutHeadlines.getTabAt(0)?.setText(categoryName)
            if (categoryName=="Dori Collection"){
                tablayoutHeadlines.getTabAt(1)?.setText("Bags collection")
            }
            else{
                tablayoutHeadlines.getTabAt(1)?.setText("Dori Collection")

            }
            tablayoutHeadlines.getTabAt(2)?.setText("Featured Products")
            tablayoutHeadlines.getTabAt(3)?.setText("Furniture and Decor")
            tablayoutHeadlines.getTabAt(4)?.setText("Gadgets")
            tablayoutHeadlines.getTabAt(5)?.setText("Jhumka Collection")
        }
    }

    private fun setUpTabLayout() {
        with(categoriesDetailViewModel) {



        }

//        val viewPager=SlidingAdapterCategory(
//            this.supportFragmentManager,Constants.categories,
//            Constants.categories.size
//        )
        with(viewDataBinding) {
//          viewpagerHeadlines.adapter=viewPager
//           tablayoutHeadlines.setupWithViewPager(viewpagerHeadlines)
//            viewpagerHeadlines.setCurrentItem(tab,true)
//            viewpagerHeadlines.offscreenPageLimit=0


        }


    }


    private fun initView() {
        with(viewDataBinding) {


            tvCategoryName.setText(categoryName)
            topBar.setOnClickListener {
                finish()
            }
        }
    }

//    companion object {
//        private fun getCategoryId(categoriesDetailActivity: CategoriesDetailActivity): Int? {
//            return categoriesDetailActivity.categoryId
//        }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onItemSelect(position: Int, itemList: NewProductResponseItem) {

    }

    override fun onLayoutAddClick(position: Int, itemList: NewProductResponseItem) {

        mainActivity!!.badge.number=ShoppingCart.getCart().size
    }

    override fun onAddClick(position: Int, itemList: NewProductResponseItem) {
        mainActivity!!.badge.number=ShoppingCart.getCart().size

    }

    override fun onSubtractClick(position: Int, itemList: NewProductResponseItem) {
        mainActivity!!.badge.number=ShoppingCart.getCart().size

    }
}
