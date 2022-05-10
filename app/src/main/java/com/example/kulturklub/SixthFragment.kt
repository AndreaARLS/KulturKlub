package com.example.kulturklub

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kulturklub.databinding.FragmentSixthBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SixthFragment : Fragment() {

    private var _binding: FragmentSixthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSixthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = arguments?.getInt("id") ?:-1

        setHasOptionsMenu(true)
        activity?.title= "Detalles de evento"

        val titulo = binding.detalleNombre
        titulo.setText((activity as MainActivity).modelo.eventos[id].titulo)
        val tipo = binding.detalleTipo
        tipo.setText((activity as MainActivity).modelo.eventos[id].tipo)
        val lugar = binding.detalleLugarCiudad
        lugar.setText((activity as MainActivity).modelo.eventos[id].lugar + " (" + (activity as MainActivity).modelo.eventos[id].lugar +")" )
        val fechas = binding.detalleFechas
        if ((activity as MainActivity).modelo.eventos[id].fechaFin.equals("")){
            fechas.setText((activity as MainActivity).modelo.eventos[id].fechaInicio)
        } else {
            fechas.setText((activity as MainActivity).modelo.eventos[id].fechaInicio + " - " + (activity as MainActivity).modelo.eventos[id].fechaFin)
        }
        val descripcion = binding.detalleDescripcion
        descripcion.setText((activity as MainActivity).modelo.eventos[id].descripcion)



        binding.editIcon3.setOnClickListener(){
            val bundle = bundleOf("id" to id)
            this.findNavController().navigate(R.id.action_thirdFragment_to_fifthFragment, bundle)
        }

        binding.deleteIcon3.setOnClickListener(){

            val bundle = bundleOf("id" to id, "name" to titulo)
            (activity as MainActivity).popupDelete(bundle)

        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.miUsuario)?.isVisible = true
        menu.findItem(R.id.organizador)?.isVisible = true
        menu.findItem(R.id.logout)?.isVisible = true
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}