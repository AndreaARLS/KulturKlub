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
import com.bumptech.glide.Glide

class Adaptador(var fragmento: Fragment , var eventos: MutableList<Evento>, var activity : MainActivity) : RecyclerView.Adapter<Adaptador.ViewHolder>() {


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tipo: TextView
        var titulo: TextView
        var ciudad: TextView
        var fechas: TextView
        var foto: ImageView


        var botonEdit : ImageView
        var botonDelete : ImageView

        var posicion = -1

        init {
            tipo = v.findViewById(R.id.tipo)
            titulo = v.findViewById(R.id.Titulo)
            ciudad = v.findViewById(R.id.ciudad)
            fechas = v.findViewById(R.id.fechas)
            foto = v.findViewById(R.id.fotoEvento)
            botonEdit = v.findViewById(R.id.editIcon)
            botonDelete = v.findViewById(R.id.deleteIcon)
            v.setOnClickListener(){
                val miBundle: Bundle = bundleOf("id" to this.posicion)
                fragmento.findNavController().navigate(R.id.action_thirdFragment_to_sixthFragment, miBundle)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_contenedor, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tipo.text = eventos[position].tipo
        holder.titulo.text = eventos[position].titulo
        holder.ciudad.text = eventos[position].lugar + " (" + eventos[position].ciudad + ")"
        if (eventos[position].fechaFin.equals("")){
            holder.fechas.text = eventos[position].fechaInicio
        } else {
            holder.fechas.text = eventos[position].fechaInicio + " - " + eventos[position].fechaFin
        }
        Glide.with(activity).load(eventos[position].foto).into(holder.foto)


        holder.botonEdit.setOnClickListener(){
            val bundle = bundleOf("id" to position)
            fragmento.findNavController().navigate(R.id.action_thirdFragment_to_fifthFragment, bundle)
        }

        holder.botonDelete.setOnClickListener(){

            val bundle = bundleOf("id" to eventos[position].id, "name" to eventos[position].titulo)
            activity.popupDelete(bundle)

        }




    }





}