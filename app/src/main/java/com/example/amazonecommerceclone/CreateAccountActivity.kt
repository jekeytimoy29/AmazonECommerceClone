package com.example.amazonecommerceclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.amazonecommerceclone.databinding.ActivityMainBinding

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_create_account)
    }

    fun continueButtonOnClick(view: View) {
        onBackPressed()
    }
}