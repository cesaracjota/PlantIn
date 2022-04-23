package com.example.plantin

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.plantin.AdaptadorElementos
import com.example.plantin.R.string.urlAPI
import kotlinx.android.synthetic.main.activity_menu_principal.*
import org.json.JSONException

class MenuPrincipal : AppCompatActivity() {
    //private var adapter: RecyclerView.Adapter<AdaptadorElementos.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_menu_principal)
        cargarLista()

    }

    fun cargarLista() {
        my_recycler.layoutManager = LinearLayoutManager(this)

        var llenarLista = ArrayList<Elemento>()
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = "http://localhost:8080/productos"

            val stringRequest = JsonArrayRequest(url,
                { response ->
                    try {
                        for (i in 0 until response.length()) {
                            val id =
                                response.getJSONObject(i).getString("_id")
                            val rfc =
                                response.getJSONObject(i).getString("rfc")
                            val nombre =
                                response.getJSONObject(i).getString("nombre")
                            val informacion =
                                response.getJSONObject(i).getString("informacion")
                            val imagen =
                                response.getJSONObject(i).getString("img")
                            llenarLista.add(Elemento(id,rfc, nombre, informacion, imagen))
                        }
                        val adapter = AdaptadorElementos(llenarLista)
                        //adapter = AdaptadorElementos(llenarLista)
                        //my_recycler.adapter = adapter
                        my_recycler?.adapter = adapter


                    } catch (e: JSONException) {
                        Toast.makeText(
                            applicationContext,
                            "Error al obtener los datos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, {
                    Toast.makeText(
                        this,
                        "Verifique que esta conectado a internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
}