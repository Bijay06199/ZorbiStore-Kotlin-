package com.example.zorbistores.ui.main.categories

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.andrognito.flashbar.Flashbar
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseFragment
import com.example.zorbistores.constants.Constants
import com.example.zorbistores.databinding.FragmentCategoriesBinding
import com.example.zorbistores.ui.main.MainActivity
import com.example.zorbistores.ui.main.categories.adapter.CategoriesAdapter
import com.example.zorbistores.ui.main.categories.categoriesDetail.CategoriesDetailActivity
import com.example.zorbistores.ui.main.categories.response.CategoriesResponse
import com.example.zorbistores.utils.AuthListenerInfo
import com.example.zorbistores.utils.extentions.dangerFlashBar
import com.example.zorbistores.utils.extentions.infoFlashBar
import com.example.zorbistores.utils.extentions.successFlashBar
import com.example.zorbistores.utils.extentions.warningFlashBar
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesFragment() : BaseFragment<FragmentCategoriesBinding, CategoriesViewModel>(),
    CategoriesAdapter.OnItemClickListener, AuthListenerInfo {

    lateinit var categoriesAdapter: CategoriesAdapter
    override fun getLayoutId(): Int = R.layout.fragment_categories
    override fun getViewModel(): CategoriesViewModel = categoriesViewModel
    private val categoriesViewModel: CategoriesViewModel by viewModel()

    var flashbar:Flashbar?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewCategoriesItem()
        initView()
    }

    private fun initView() {
        with(viewDataBinding){

            topBar.setOnClickListener {
                startActivity(Intent(this@CategoriesFragment.activity,MainActivity::class.java))
            }

            var animation= AnimationUtils.loadAnimation(requireContext(),R.anim.rotation_anim)
            animation.setInterpolator ( LinearInterpolator() )
            progressBar.startAnimation(animation)

        }
    }

    private fun setUpRecyclerViewCategoriesItem() {
        with(viewDataBinding) {
            with(categoriesViewModel) {
                categoriesViewModel.authListenerInfo=this@CategoriesFragment
                var itemList = ArrayList<CategoriesResponse>()

                categoriesAdapter = CategoriesAdapter(requireContext(),this@CategoriesFragment,itemList)
                viewDataBinding.recyclerviewCategories.adapter = categoriesAdapter

                categories()

                categoriesEvent.observe(viewLifecycleOwner, Observer {

                        itemList.addAll(categoryName!!)
                        categoriesAdapter.notifyDataSetChanged()
                        progressBar.visibility=View.GONE
                })

            }


        }
    }

    companion object {
        val TAG = CategoriesFragment::class.java.simpleName
        fun start(activity: FragmentActivity, containerId: Int) {
            val fragment = CategoriesFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(TAG)
                .commit()
        }
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

    override fun onSelectListener(position: Int, itemList: CategoriesResponse) {
//        startActivity(Intent(this.activity,CategoriesDetailActivity::class.java))

        var allCategoryName=itemList.name

        for (i in 0..position){
            var categoryName=itemList.name
            var categoryId=itemList.id

            val intent=Intent(this.activity,CategoriesDetailActivity::class.java)
            intent.putExtra(Constants.CategoryName,categoryName)
            intent.putExtra(Constants.CategoryId,categoryId)
            startActivity(intent)

        }

    }


}
