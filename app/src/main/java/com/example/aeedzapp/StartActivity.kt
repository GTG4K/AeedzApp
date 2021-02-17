package com.example.aeedzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        startBtnSignIn.setOnClickListener {
            val signInIntent = Intent(this, LogInActivity::class.java)
            startActivity(signInIntent)
            overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top)
        } //Sign in button

        startBtnSignUp.setOnClickListener {
            val signUpIntent = Intent(this, RegisterActivity::class.java)
            startActivity(signUpIntent)
        } //email Sign up button

    }

}