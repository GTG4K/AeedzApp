package com.example.aeedzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_start.*

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        logInBtnBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_from_top,R.anim.slide_to_bottom)
        } //Back to start page

        signInForgotPwd.setOnClickListener{
            val recoverPwdIntent = Intent(this, RecoverPwdActivity::class.java)
            startActivity(recoverPwdIntent)
        }
    }
}