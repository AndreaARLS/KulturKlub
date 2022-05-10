package com.example.kulturklub

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.kulturklub.databinding.FragmentForthBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ForthFragment : Fragment() {

    private var _binding: FragmentForthBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForthBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        activity?.title= "Nuevo Evento"

        val spinner = binding.newTipoSelect
        val tipos = listOf("CONCIERTO", "CICLO DE CINE", "EXPOSICIÓN")
        val adaptadorSelect = ArrayAdapter(activity as MainActivity, android.R.layout.simple_spinner_item, tipos)
        spinner.adapter = adaptadorSelect
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tutorialsName: String = parent.getItemAtPosition(position).toString()
                Toast.makeText(parent.context, "Selected: $tutorialsName", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val botonGuardar = binding.guardarNuevoEvento

        botonGuardar.setOnClickListener {
            var titulo = binding.newTituloInput.text.toString()
            var tipo = binding.newTipoSelect.selectedItem.toString()
            var descripcion = binding.newDescripcionInput.text.toString()
            var lugar = binding.newLugarInput.text.toString()
            var ciudad = binding.newCiudadInput.text.toString()
            var fechaIni = binding.newFechaInput.text.toString()
            var fechafin = binding.newFechafinInput.text.toString()
            var imgurl = binding.newUrlInput.text.toString()
            var creator = (activity as MainActivity).currentUser
            if (!titulo.equals("") && !tipo.equals("") && !lugar.equals("") && !ciudad.equals("") && !fechaIni.equals("")){
                (activity as MainActivity).modelo.nuevoEvent(titulo, tipo, descripcion, lugar, ciudad, fechaIni, fechafin, imgurl, creator)
                findNavController().navigate(R.id.action_forthFragment_to_thirdFragment)
            }
        }

        val botonCancelar = binding.cancelbutton

        botonCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_forthFragment_to_thirdFragment)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.miUsuario)?.isVisible = false
        menu.findItem(R.id.organizador)?.isVisible = false
        menu.findItem(R.id.logout)?.isVisible = true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}