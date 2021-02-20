package com.example.aeedzapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity : AppCompatActivity() {

    lateinit var mProgressDialog: Dialog

    //snack bar for showing Errors
    fun showErrorSnackBar(message: String, isError: Boolean){
        val snackBar = Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_SHORT)
        val snackBarView = snackBar.view

        if (isError){
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.AeedzLight
                )
            )
        }else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.Success
                )
            )
        }
        snackBar.show()
    }


    //Showing the loading screen
    fun showProgressDialog(text: String){
        mProgressDialog = Dialog(this)

        //set pre-made progress bar as a resource
        mProgressDialog.setContentView(R.layout.dialog_progress)

        mProgressDialog.tv_ProgressText.text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        //start progress dialog [lading screen]
        mProgressDialog.show()
    }

    //hiding the loading screen
    fun hideProgressDialog(){
        mProgressDialog.hide()
    }
}