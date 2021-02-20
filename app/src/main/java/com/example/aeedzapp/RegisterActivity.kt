package com.example.aeedzapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth


        btn_BackReg.setOnClickListener {
            finish()
        } //Sign in button

        btn_Register.setOnClickListener {
            registerUser()
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }



    /*ValidateRegisterDetails() checks the user input and makes sure
    it is valid for registration.*/
    private fun validateRegisterDetails(): Boolean{
        return when{
            TextUtils.isEmpty(et_Username.text.toString().trim { it <=' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_username), true)
                false
            }
            TextUtils.isEmpty(et_Email.text.toString().trim { it <=' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(et_Pwd.text.toString().trim { it <=' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_pwd), true)
                false
            }
            TextUtils.isEmpty(et_RepeatPwd.text.toString().trim { it <=' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_repeat_pwd), true)
                false
            }
            et_Pwd.text.toString().trim { it <=' '} != et_RepeatPwd.text.toString().trim { it <=' '} -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_pwd_mismatch), true)
                false
            }
            else -> {
                true
            }
        }
    }


    /*RegisterUser() creates the used and adds it to the Firebase Database.*/
    private fun registerUser(){
        if(validateRegisterDetails()){

            showProgressDialog(resources.getString(R.string.please_wait))

            val email = et_Email.text.toString().trim{it <= ' '}
            val pwd = et_Pwd.text.toString().trim{it <= ' '}

            auth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this) { task ->

                    hideProgressDialog()

                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
//                        showErrorSnackBar(resources.getString(R.string.registered_successfully),false)

                    } else {
                        // If sign in fails, display a message to the user.
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                    }
                }
        }
    }
}