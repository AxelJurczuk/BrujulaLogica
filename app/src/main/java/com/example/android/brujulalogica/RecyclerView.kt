package com.example.android.brujulalogica

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_recycler_view.*

enum class ProviderType {
    BASIC
}

class RecyclerView : AppCompatActivity(), ExampleAdapter.OnItemClickListener {
    private val exampleList = generateList()
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        //RecyclerView
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        //Setup LogIn/LogOut
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setUp(email ?: "", provider ?: "")

        //Save authentication data
        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

    }

    private fun setUp(email: String, provider: String) {
        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider

        logOutButton.setOnClickListener {
            //Delete logIn data
            val prefs =
                getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

    override fun onItemClick(position: Int) {

        //Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
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