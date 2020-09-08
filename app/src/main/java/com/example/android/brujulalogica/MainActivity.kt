package com.example.android.brujulalogica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private val exampleList = generateList()
    private val adapter = ExampleAdapter(exampleList, this)

    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    private lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.i ("onCreated called")

        setContentView(R.layout.activity_main)

        //RecyclerView
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        //Setup LogIn/LogOut
        setUp()

        //Show current user's email on screen
        auth = FirebaseAuth.getInstance()
        textView = findViewById(R.id.emailTextView)
        val user = auth.currentUser
        user?.let{
            textView.text = user.email.toString()
        }

        //listener to check if the user is logged in
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            if (firebaseUser == null) {
                val intent = Intent(this, LoginScreen::class.java)
                startActivity(intent)
                finish()
            }
        }

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


    private fun setUp() {
        title = "Inicio"

        //Cerrar sesión
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }


    override fun onItemClick(position: Int) {

        when (position) {
            4 -> {
                val intent1 = Intent(this, NewActivity::class.java)
                startActivity(intent1)
            }
            else -> {
                val intent1 = Intent(this, NewActivity::class.java)
                startActivity(intent1)
            }
        }
    }

    /*
buscar una mejor forma de hacer la lista
por que es mejor crear un metodo generateList() en lugar de poner todo en onCreate?
*/
    private fun generateList(): List<ItemClass> {
        val list = ArrayList<ItemClass>()


        list.add(ItemClass(R.drawable.ic_add_vector, "Calendaria"))
        list.add(ItemClass(R.drawable.ic_add_vector, "Cuarentena Personal"))
        list.add(ItemClass(R.drawable.ic_add_vector, "Ingrese una Palabra"))
        list.add(ItemClass(R.drawable.ic_add_vector, "Radio de Palabras"))
        list.add(ItemClass(R.drawable.ic_calendar, "Ingresa una fecha "))
        list.add(ItemClass(R.drawable.ic_add_vector, "Anillo de Fuego"))
        list.add(ItemClass(R.drawable.ic_add_vector, "Etapas / Segmentos 5777"))
        list.add(ItemClass(R.drawable.ic_add_vector, "Imagenes de Referencia"))
        list.add(ItemClass(R.drawable.ic_add_vector, "Agregar o Quitar tiempo a una fecha"))
        list.add(ItemClass(R.drawable.ic_add_vector, "Calcular Fecha"))


        return list
    }


}