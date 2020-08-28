package com.example.android.brujulalogica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerView : AppCompatActivity(), ExampleAdapter.OnItemClickListener {
    private val exampleList = generateList()
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)


        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        when (position) {
                4-> {
                    val intent1 = Intent(this, IngresoFecha::class.java)
                    startActivity(intent1)
                }
                else -> {
                    val intent1 = Intent(this, RandomActivity::class.java)
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