package com.example.taskketchum

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.txt_username)
        val password = findViewById<EditText>(R.id.txt_password)

        val loginBtn = findViewById<Button>(R.id.btn_login)

        loginBtn.setOnClickListener {
            login(username.text.toString(), password.text.toString())
        }
    }

    private fun login(username: String?, password: String?) {
        if (username == "Holascupido" && password == "Holas123") {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            this@LoginActivity.startActivity(intent)
        }
    }
}