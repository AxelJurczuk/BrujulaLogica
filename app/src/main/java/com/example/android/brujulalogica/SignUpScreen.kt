package com.example.android.brujulalogica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_screen.*
import kotlinx.android.synthetic.main.sign_up_screen.*

class SignUpScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_screen)

        signIn ()
    }

    private fun signIn() {
        signInButton.setOnClickListener {

            if (email_editText.text.isNotEmpty() && password_editText.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        email_editText.text.toString(),
                        password_editText.text.toString()
                    )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, RecyclerView::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            showAlert()
                        }
                    }
            }
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
}