package com.example.aeedzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recover_pwd.*

class RecoverPwdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_pwd)

        recoverAccountBackBtn.setOnClickListener{
            finish()
        }
    }


}