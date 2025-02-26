package com.android.asatabai

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog

class SettingsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        handleLogout()
        handleAboutDevelopers()
    }

    private fun handleLogout(){
        val btnLogOut = findViewById<Button>(R.id.logout)
        btnLogOut.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes") { _, _ ->
                    // Navigate to the Sign-in screen
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish() // Close current activity
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    private fun handleNightMode() {

    }

    private fun handleLanguage() {

    }
    private fun handleNotifications() {

    }

    private fun handleAboutDevelopers() {
        val aboutButton = findViewById<Button>(R.id.developer)
        aboutButton.setOnClickListener {
            startActivity(Intent(this, DevelopersActivity::class.java))
        }
    }
}