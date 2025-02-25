package com.android.asatabai

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val text_regis = findViewById<TextView>(R.id.signup)
        text_regis.setOnClickListener {
            Log.e("CSIT284", "Text is Clicked!")
            Toast.makeText(this, "I'll just stop at the sign-up page, kuya.", Toast.LENGTH_LONG).show()
            val intent = Intent(this, RegisterActivity :: class.java)
            startActivity(intent)
        }
        val btn_login = findViewById<Button>(R.id.btnSignIn)
        btn_login.setOnClickListener {
            val intent = Intent(this, SettingsActivity :: class.java)
            startActivity(intent)
        }
    }
}