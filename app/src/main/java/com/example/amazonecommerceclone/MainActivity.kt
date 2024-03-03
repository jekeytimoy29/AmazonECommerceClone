package com.example.amazonecommerceclone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.amazonecommerceclone.databinding.ActivityMainBinding
import com.example.amazonecommerceclone.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var usersList: MutableList<User>

    private val resultContracts =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val responseData: Intent? = result.data
                val jsonString = responseData?.getStringExtra("user")
                val type = object : TypeToken<User>() {}.type
                val user = Gson().fromJson(jsonString, type) as User
                usersList.add(user)
                displayMessage(getString(R.string.added_user_successfully, user.name))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        usersList = mutableListOf<User>(
            User("Jake Desneyk", "snake@fakegmail.com", "snakePWORD@3", null),
            User("Jakey Snakey", "snakey@fakegmail.com", "snakeyPWORD@3", null),
            User("Jekey Timoy", "timoy@fakegmail.com", "timoyPWORD@3", null)
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun createAccountOnClick(view: View) {
        val intent = Intent(this, CreateAccountActivity::class.java).apply {
            val jsonString = Gson().toJson(usersList)
            putExtra("usersList", jsonString)
        }

        resultContracts.launch(intent)
    }

    fun signInButtonOnClick(view: View) {
        val intent = Intent(this, ShoppingCategoryActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        binding.editUsername.text.clear()
        binding.editPassword.text.clear()
        binding.showPassword.isChecked = false
        binding.keepMeSignedIn.isChecked = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val jsonString = Gson().toJson(usersList)
        outState.putString("usersList", jsonString)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            val jsonString = savedInstanceState.getString("usersList")
            val type = object : TypeToken<MutableList<User>>() {}.type
            usersList = Gson().fromJson(jsonString, type)
        }
    }

    private fun displayMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}