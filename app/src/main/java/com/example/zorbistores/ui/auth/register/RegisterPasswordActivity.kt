package com.example.zorbistores.ui.auth.register

import android.os.Bundle
import androidx.lifecycle.Observer
import com.andrognito.flashbar.Flashbar
import com.example.zorbistores.BR
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseActivity
import com.example.zorbistores.databinding.ActivityRegisterPasswordBinding
import com.example.zorbistores.utils.AuthListenerInfo
import com.example.zorbistores.utils.extentions.dangerFlashBar
import com.example.zorbistores.utils.extentions.infoFlashBar
import com.example.zorbistores.utils.extentions.successFlashBar
import com.example.zorbistores.utils.extentions.warningFlashBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterPasswordActivity : BaseActivity<ActivityRegisterPasswordBinding,RegisterPasswordViewModel>(),AuthListenerInfo {


    var flashbar: Flashbar?=null
    override fun getLayoutId(): Int=R.layout.activity_register_password
    override fun getViewModel(): RegisterPasswordViewModel =registerPasswordViewModel
    private val registerPasswordViewModel:RegisterPasswordViewModel by viewModel()
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setUpObservers()
    }

    private fun setUpObservers() {
        with(viewDataBinding){
            with(registerPasswordViewModel){
                backClickedEvent.observe(this@RegisterPasswordActivity, Observer {
//                    viewDataBinding.back.setColorFilter(
//                        viewDataBinding.back.context.resources.getColor(R.color.pressed_back),
//                        PorterDuff.Mode.MULTIPLY
//                    )
                    finish()
                })
            }
        }
    }

    private fun initView() {
        with(viewDataBinding){
            registerPasswordViewModel.authListenerInfo=this@RegisterPasswordActivity

            tvName.setText(preferenceManager.getFirstName())
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
}