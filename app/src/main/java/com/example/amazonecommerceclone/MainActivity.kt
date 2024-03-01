package com.example.amazonecommerceclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.amazonecommerceclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun createAccountOnClick(view: View) {
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
    }

    fun signInButtonOnClick(view: View) {
        val intent = Intent(this, ShoppingCategoryActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        binding.editUsername.text.clear()
        binding.editPassword.text.clear()
        binding.showPassword.isChecked = false
        binding.keepMeSignedIn.isChecked = false
    }
}