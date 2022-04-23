package com.example.plantin

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class AdaptadorElementos(val ListaElementos:ArrayList<Elemento>): RecyclerView.Adapter<AdaptadorElementos.ViewHolder>() {

    private fun obtener_imagen(url: String): Bitmap? {
        var bm: Bitmap? = null
        try {
            val _url = URL(url)
            val con: URLConnection = _url.openConnection()
            con.connect()
            val ist: InputStream = con.getInputStream()
            val bis = BufferedInputStream(ist)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            ist.close()
        } catch (e: IOException) {
        }
        return bm
    }

    override fun getItemCount(): Int {
        return ListaElementos.size;
    }
    class ViewHolder (itemView : View):RecyclerView.ViewHolder(itemView) {
        val fImagen = itemView.findViewById<ImageView>(R.id.plant_image);
        val fTitle = itemView.findViewById<TextView>(R.id.plant_title)
    }
    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder?.fImagen?.setImageBitmap(obtener_imagen(ListaElementos[position].img))
        holder?.fTitle?.text=ListaElementos[position].nombre
        //holder?.fConductor?.text=ListaElementos[position].driver_name
        //holder?.fModelo?.text=ListaElementos[position].model

    /*
        var id = ListaElementos[position]._id
        var nombre = ListaElementos[position].nombre
        //var modelo = ListaElementos[position].model
        //var capacidad = ListaElementos[position].capacity
        //var fecha_cad = ListaElementos[position].insurance_renewal_date
        var imagen = ListaElementos[position].img*/

        //holder.itemView.setOnClickListener(){
        /*
        holder.itemView.setOnClickListener(){

            val llamaractividad = Intent(holder.itemView.context,moreInfo::class.java)
            llamaractividad.putExtra("id",id.toString())
            llamaractividad.putExtra("placa",placa)
            llamaractividad.putExtra("modelo",modelo)
            llamaractividad.putExtra("capacidad",capacidad.toString())
            llamaractividad.putExtra("fecha",fecha_cad.toString())
            llamaractividad.putExtra("image", imagen)
            holder.itemView.context.startActivity(llamaractividad)
        }*/
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.listaplantas,
                parent, false);
        return ViewHolder(v);
    }
}
