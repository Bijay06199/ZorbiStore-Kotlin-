package com.example.zorbistores.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.graphics.PorterDuff
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import com.andrognito.flashbar.Flashbar
import com.example.zorbistores.BR
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseActivity
import com.example.zorbistores.databinding.ActivityRegisterFinalBinding
import com.example.zorbistores.utils.AuthListenerInfo
import com.example.zorbistores.utils.extentions.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFinalActivity : BaseActivity<ActivityRegisterFinalBinding,RegisterFinalViewModel>(),AuthListenerInfo {

    var flashbar: Flashbar?=null
    override fun getLayoutId(): Int =R.layout.activity_register_final
    override fun getViewModel(): RegisterFinalViewModel =registerFinalViewModel
    private val registerFinalViewModel:RegisterFinalViewModel by viewModel()
    override fun getBindingVariable(): Int {
        return BR.viewModel


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setUpObservers()

    }

    private fun initView() {
        registerFinalViewModel.authListenerInfo=this

        viewDataBinding.progressBar4.visibility= View.GONE
    }


    private fun setUpObservers() {
        with(registerFinalViewModel){
            backClickedEvent.observe(this@RegisterFinalActivity, Observer {
                val intent= Intent(this@RegisterFinalActivity,RegisterPasswordActivity::class.java)
                startActivity(intent)
                intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TOP

                viewDataBinding.back.setColorFilter(
                        viewDataBinding.back.context.resources.getColor(R.color.pressed_back),
                        PorterDuff.Mode.MULTIPLY
                )
            })

        }
    }

    override fun onSuccess(message: String) {

        flashbar=successFlashBar(message)
        flashbar?.show()
        viewDataBinding.progressBar4.visibility=View.GONE
        viewDataBinding.tvRegister.showText()
    }

    override fun onStarted() {
        viewDataBinding.progressBar4.visibility=View.GONE
        viewDataBinding.tvRegister.hideText()

    }

    override fun onInfo(message: String) {
        flashbar=infoFlashBar(message)
        flashbar?.show()
        viewDataBinding.progressBar4.visibility=View.VISIBLE
        var animation= AnimationUtils.loadAnimation(this,R.anim.rotation_anim)
        animation.setInterpolator ( LinearInterpolator() )
        viewDataBinding.progressBar4.startAnimation(animation)
    }

    override fun onWarning(message: String) {
        flashbar=warningFlashBar(message)
        flashbar?.show()
        viewDataBinding.progressBar4.visibility=View.GONE
        viewDataBinding.tvRegister.showText()
    }

    override fun onDanger(message: String) {
        flashbar=dangerFlashBar(message)
        flashbar?.show()
        viewDataBinding.progressBar4.visibility=View.GONE

    }
}