package com.example.kulturklub
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class Adaptador(var fragmento: Fragment , var lista: MutableList<Evento>, var activity : MainActivity) : RecyclerView.Adapter<Adaptador.ViewHolder>() {


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tipo: TextView
        var titulo: TextView
        var ciudad: TextView
        var fechainicio: TextView
        var fechafin: TextView


        var botonEdit : ImageView
        var botonDelete : ImageView

        var posicion= -1

        init {
            tipo = v.findViewById(R.id.tipo)
            titulo = v.findViewById(R.id.Titulo)
            ciudad = v.findViewById(R.id.ciudad)
            fechainicio = v.findViewById(R.id.fechainicio)
            fechafin = v.findViewById(R.id.fechafin)
            botonEdit = v.findViewById(R.id.editIcon)
            botonDelete = v.findViewById(R.id.deleteIcon)
            v.setOnClickListener(){
                val miBundle: Bundle = bundleOf("id" to this.posicion)
                fragmento.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, miBundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_contenedor, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tipo.text = lista[position].tipo
        holder.titulo.text = lista[position].titulo
        holder.ciudad.text = lista[position].ciudad
        holder.fechainicio.text = lista[position].fechaInicio
        holder.fechafin.text = lista[position].fechaFin

        holder.botonEdit.setOnClickListener(){
            val bundle = bundleOf("id" to position)
            fragmento.findNavController().navigate(R.id.action_thirdFragment_to_fifthFragment, bundle)
        }

        holder.botonDelete.setOnClickListener(){

            //val bundle = bundleOf("id" to position, "name" to lista[position].titulo)
            //activity.popupDelete(bundle)

        }

    }


}