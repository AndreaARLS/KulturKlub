package com.example.kulturklub

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kulturklub.databinding.FragmentSixthBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SixthFragment : Fragment() {

    private var _binding: FragmentSixthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var eventid : String = ""
    lateinit var evento : Evento

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSixthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var position = arguments?.getInt("id")?: -1
        val eventos : MutableList<Evento> = (activity as MainActivity).modelo.consultarEventos()
        evento = eventos[position]
        eventid = evento.id


        setHasOptionsMenu(true)
        activity?.title= "Detalles de evento"

        val titulo = binding.detalleNombre
        titulo.setText(evento.titulo)
        val tipo = binding.detalleTipo
        tipo.setText(evento.tipo)
        val lugar = binding.detalleLugarCiudad
        lugar.setText(evento.lugar + " (" + evento.ciudad +")" )
        val fechas = binding.detalleFechas
        if (evento.fechaFin.equals("")){
            fechas.setText(evento.fechaInicio)
        } else {
            fechas.setText(evento.fechaInicio + " - " + evento.fechaFin)
        }
        val descripcion = binding.detalleDescripcion
        descripcion.setText(evento.descripcion)
        val imagen = binding.imageEvento
        Glide.with(this).load(evento.foto).into(imagen)
                
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem((activity as MainActivity).home)?.isVisible = true
        menu.findItem((activity as MainActivity).usermenu)?.isVisible = true
        menu.findItem((activity as MainActivity).creatormenu)?.isVisible = true
        menu.findItem((activity as MainActivity).logoutmenu)?.isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle1 = bundleOf("id" to (activity as MainActivity).currentUser)
        val bundle2 = bundleOf("id" to evento.creador)
        when (item.itemId) {
            (activity as MainActivity).home -> findNavController().navigate(R.id.action_sixthFragment_to_thirdFragment)
            (activity as MainActivity).usermenu -> findNavController().navigate(R.id.action_sixthFragment_to_seventhFragment, bundle1)
            (activity as MainActivity).creatormenu -> {
                findNavController().navigate(R.id.action_sixthFragment_to_seventhFragment, bundle2)
            }
            (activity as MainActivity).logoutmenu -> {
                (activity as MainActivity).currentUser = ""
                findNavController().navigate(R.id.action_sixthFragment_to_FirstFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}