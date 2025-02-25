package com.android.asatabai

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import java.util.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var nightModeSwitcher: SwitchCompat
    private lateinit var notificationSwitch: SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        sharedPreferences = getSharedPreferences("MODE", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        handleNightMode()
        handleLanguage()
        handleNotifications()
        handleAboutDevelopers()
        handleLogout()
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

        nightModeSwitcher = findViewById(R.id.night_mode_switch)
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)

        nightModeSwitcher.setOnClickListener { view->
            val isNightMode = nightModeSwitcher.isChecked
            if(isNightMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor = sharedPreferences.edit()
                editor.putBoolean("night", false)
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor = sharedPreferences.edit()
                editor.putBoolean("night", true)
            }
            editor.apply()
        }
    }

    private fun handleLanguage() {
        val languageSpinner = findViewById<Spinner>(R.id.language_spinner)
        val languages = arrayOf("English", "Filipino", "Cebuano")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
        languageSpinner.adapter = adapter
        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var selectedLanguage = when (position) {
                    0 -> "en"
                    1 -> "fil"
                    2 -> "ceb"
                    else -> "en"
                }
                setLocale(selectedLanguage)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        editor.putString("language", language)
        editor.apply()
    }

    private fun handleNotifications() {
        notificationSwitch = findViewById(R.id.notifications_switch)
        var isChecked = notificationSwitch.isChecked

        notificationSwitch.setOnCheckedChangeListener { _, notificationS->
            editor.putBoolean("notifications", isChecked)
            editor.apply()
            if(notificationSwitch.isChecked && isChecked != notificationSwitch.isChecked){
                Toast.makeText(this, "Notifications On", Toast.LENGTH_LONG).show()
                isChecked = notificationSwitch.isChecked
            }else if(isChecked != notificationSwitch.isChecked){
                Toast.makeText(this, "Notifications Off", Toast.LENGTH_LONG).show()
                isChecked = notificationSwitch.isChecked
            }
        }
    }

    private fun handleAboutDevelopers() {
        val aboutButton = findViewById<Button>(R.id.developer)
        aboutButton.setOnClickListener {
            startActivity(Intent(this, DevelopersActivity::class.java))
        }
    }
}