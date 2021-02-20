package com.example.aeedzapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_log_in.et_Email
import kotlinx.android.synthetic.main.activity_log_in.et_Pwd
import kotlinx.android.synthetic.main.activity_register.*

class LogInActivity : BaseActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        auth = Firebase.auth
    }

    private fun loginUser(){
        if(validateLoginDetails()){

            showProgressDialog(resources.getString(R.string.please_wait))

            val email = et_Email.text.toString().trim{it <= ' '}
            val pwd = et_Pwd.text.toString().trim{it <= ' '}

            auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(this) { task ->

                hideProgressDialog()

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val mainIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    showErrorSnackBar(task.exception!!.message.toString(),true)
//                  updateUI(null)
                }
            }
        }
    }

    override fun onClick(view: View?){
        if (view!= null)
            when(view.id){
                R.id.tv_ForgotPwd -> {
                    val recoverPwdIntent = Intent(this, RecoverPwdActivity::class.java)
                    startActivity(recoverPwdIntent)
                }

                R.id.btn_Back -> {
                    finish()
                    overridePendingTransition(R.anim.slide_from_top,R.anim.slide_to_bottom)
                } //Back to start page

                R.id.btn_Login ->{
                    loginUser()
                }
            }
        }

    private fun validateLoginDetails(): Boolean{
        return when{
            TextUtils.isEmpty(et_Email.text.toString().trim{it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email),true)
                false
            }
            TextUtils.isEmpty(et_Pwd.text.toString().trim{it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_pwd),true)
                false
            }
            else -> { true }
        }
    }
}

