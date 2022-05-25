package com.example.kulturklub

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
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
    var evento : MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSixthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventid = arguments?.getString("id")?: ""
        var eventos : MutableList<MutableList<String>> = (activity as MainActivity).modelo.consultarEventos()
        for (i in eventos){
            if (i[0].equals("eventid")){
                evento = i
            }
        }


        setHasOptionsMenu(true)
        activity?.title= "Detalles de evento"

        val titulo = binding.detalleNombre
        titulo.setText(evento[1])
        val tipo = binding.detalleTipo
        tipo.setText(evento[2])
        val lugar = binding.detalleLugarCiudad
        lugar.setText(evento[4] + " (" + evento[5] +")" )
        val fechas = binding.detalleFechas
        if (evento[7].equals("")){
            fechas.setText(evento[6])
        } else {
            fechas.setText(evento[6] + " - " + evento[7])
        }
        val descripcion = binding.detalleDescripcion
        descripcion.setText(evento[3])
        val imagen = binding.imageEvento
        Glide.with(this).load(evento[8]).into(imagen)



        binding.editIcon3.setOnClickListener(){
            val bundle = bundleOf("id" to eventid)
            this.findNavController().navigate(R.id.action_thirdFragment_to_fifthFragment, bundle)
        }

        binding.deleteIcon3.setOnClickListener(){

            val bundle = bundleOf("id" to eventid, "name" to titulo)
            (activity as MainActivity).popupDelete(bundle)

        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem((activity as MainActivity).usermenu)?.isVisible = true
        menu.findItem((activity as MainActivity).creatormenu)?.isVisible = true
        menu.findItem((activity as MainActivity).logoutmenu)?.isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle1 = bundleOf("id" to (activity as MainActivity).currentUser)
        val bundle2 = bundleOf("id" to (evento[0]))
        when (item.itemId) {
            (activity as MainActivity).usermenu -> findNavController().navigate(R.id.action_sixthFragment_to_seventhFragment, bundle1)
            (activity as MainActivity).creatormenu -> {
                findNavController().navigate(R.id.action_sixthFragment_to_seventhFragment, bundle2)
            }
            (activity as MainActivity).logoutmenu -> findNavController().navigate(R.id.action_sixthFragment_to_FirstFragment)
        }
        return super.onOptionsItemSelected(item)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}