package com.example.android.brujulalogica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)

        val logInButton: Button = findViewById(R.id.log_in_button)

        logInButton.setOnClickListener {
            newActivity()
        }
    }

    fun newActivity (){
        val intent = Intent (this, RecyclerView::class.java)
        startActivity(intent)
    }
}