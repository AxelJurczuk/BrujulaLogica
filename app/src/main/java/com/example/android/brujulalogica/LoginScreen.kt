package com.example.android.brujulalogica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_screen.*
import timber.log.Timber

class LoginScreen : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.i ("onCreated called")

        auth = FirebaseAuth.getInstance()

        //listener to check if the user is logged in
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                val intent = Intent(this, MainActivity::class.java)
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
        Timber.i ("onStart called")

    }


    override fun onStop() {
        super.onStop()
        auth!!.removeAuthStateListener(this.authStateListener!!)
        Timber.i ("onStop called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i ("onPause called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i ("onResume called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i ("onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i ("onRestart called")
    }

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
        }

        //Forgot password
        forgotPassButton.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
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
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(intent)
    }
}