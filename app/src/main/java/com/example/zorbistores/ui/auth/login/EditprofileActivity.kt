package com.example.zorbistores.ui.auth.login

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.zorbistores.R
import com.example.zorbistores.base.BaseActivity
import com.example.zorbistores.constants.Constants
import com.example.zorbistores.databinding.ActivityEditprofileBinding
import com.example.zorbistores.utils.bindings.GlideApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditprofileActivity : BaseActivity<ActivityEditprofileBinding,EditProfileViewModel>() {

    override fun getLayoutId(): Int =R.layout.activity_editprofile
    override fun getViewModel(): EditProfileViewModel =editProfileViewModel
    private val editProfileViewModel:EditProfileViewModel by viewModel()

    var first_Name:String?=null
    var last_Name:String?=null
    var email_pass:String?=null
    var number_pass:String?=null
    var address_pass:String?=null
    var address1_pass:String?=null
    var profileImage: Uri?=null
    var GET_FROM_GALLERY = 3
    var FINAL_TAKE_PHOTO = 1
    private val PERMISSION_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        first_Name=intent.getStringExtra(Constants.FirstName)
        last_Name=intent.getStringExtra(Constants.LastName)
        email_pass=intent.getStringExtra(Constants.Email)
        number_pass=intent.getStringExtra(Constants.Number)
        address_pass=intent.getStringExtra(Constants.Address)
        address1_pass=intent.getStringExtra(Constants.Addrss1)

        initView()
        setUpObservers()
    }

    private fun setUpObservers() {
        with(viewDataBinding){
            with(editProfileViewModel){




            }
        }
    }

    private fun initView() {

        GlideApp.with(this)
            .load(preferenceManager.getImage())
            .placeholder(R.drawable.profile_placeholder)
            .into(viewDataBinding.profileIcon)

        with(viewDataBinding){
            tvFirstName.setText(preferenceManager.getFirstName())
           firstName.setText(first_Name)
            lastName.setText(last_Name)
            mobileNumber.setText(number_pass)
            email.setText(email_pass)
            address.setText(address_pass)
            address1.setText(address_pass)
            topBar.setOnClickListener {
                finish()
            }

            profileIcon.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this@EditprofileActivity,
                        Manifest.permission.CAMERA
                    )
                    == PackageManager.PERMISSION_DENIED ||
                    ContextCompat.checkSelfPermission(
                        this@EditprofileActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    == PackageManager.PERMISSION_DENIED
                ) {
                    //permission was not enabled
                    val permission = arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    //show popup to request permission
                    ActivityCompat.requestPermissions(
                        this@EditprofileActivity,
                        permission,
                        PERMISSION_CODE
                    )
                } else {
                    pickImage()
                }
            }

        }
    }


    private fun pickImage() {

        setUpDialogCamera()
        var gallery=dialog.findViewById<TextView>(R.id.btn_gallery)
        var camera = dialog.findViewById<TextView>(R.id.btn_camera)

        gallery.setOnClickListener {

            val i = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(i, GET_FROM_GALLERY)
            dialog.dismiss()

        }

        camera.setOnClickListener {

            openCamera()
            dialog.dismiss()
        }


    }

    private fun openCamera() {

        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        profileImage = contentResolver?.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )

        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, profileImage)
        startActivityForResult(cameraIntent, FINAL_TAKE_PHOTO)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK)
            when(requestCode){
                GET_FROM_GALLERY->{
                    val selectedImage = data!!.data

                    preferenceManager.setImage(selectedImage.toString())
                    GlideApp.with(this)
                        .load(selectedImage)
                        .into(viewDataBinding.profileIcon)

                }

                FINAL_TAKE_PHOTO->{

                    preferenceManager.setImage(profileImage.toString())
                    GlideApp.with(this)
                        .load(profileImage)
                        .into(viewDataBinding.profileIcon)
                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup was granted
                    pickImage()
                } else {
                    //permission from popup was denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}