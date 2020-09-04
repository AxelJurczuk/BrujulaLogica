package com.example.android.brujulalogica

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_screen.*

class LoginScreen : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        //listener to check if the user is logged in
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                val intent = Intent(this, RecyclerView::class.java)
                startActivity(intent)
                finish()
            }
        }

        setContentView(R.layout.login_screen)

        //Setup
        setup()
    }

    override fun onStart() {
        super.onStart()
        auth!!.addAuthStateListener(this.authStateListener!!)
    }

    /* onStop??
    override fun onStop() {
        super.onStop()
        firebaseAuth!!.removeAuthStateListener(this.authStateListener!!)
    }

     */


    private fun setup() {
        title = "Autenticación"

        //Acceder
        logInButton.setOnClickListener {

            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        emailEditText.text.toString(),
                        passwordEditText.text.toString()
                    )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email ?: "")
                        } else {
                            showAlert()
                        }
                    }
            }
        }

        //Registrar
        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpScreen::class.java)
            startActivity(intent)
            finish()
        }

        //Forgot password
        forgotPassButton.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String) {
        val intent = Intent(this, RecyclerView::class.java).apply {
            putExtra("email", email)
        }
        startActivity(intent)
    }
}