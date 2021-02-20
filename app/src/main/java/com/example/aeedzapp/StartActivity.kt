package com.example.aeedzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        auth = Firebase.auth

        btn_SignIn.setOnClickListener {
            val signInIntent = Intent(this, LogInActivity::class.java)
            startActivity(signInIntent)
            overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top)
        } //Sign in button

        btn_SignUp.setOnClickListener {
            val signUpIntent = Intent(this, RegisterActivity::class.java)
            startActivity(signUpIntent)
        } //email Sign up button
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
//            reload();
        }
    } //Bypass Login screen if the user is already logged in
}