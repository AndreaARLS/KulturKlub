package com.example.kulturklub
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kulturklub.databinding.FragmentFifthBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FifthFragment : Fragment() {

    private var _binding: FragmentFifthBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = arguments?.getInt("id") ?:-1

        //setHasOptionsMenu(true)
        activity?.title= "Editar película"

        val spinner = binding.editTipoSelect
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

        val titulo = binding.editTituloInput
        titulo.setText((activity as MainActivity).modelo.eventos[id].titulo)
        val tipo = binding.editTipoSelect
        var pos = 0
        if ((activity as MainActivity).modelo.eventos[id].tipo == "CONCIERTO") {
            pos = 0
        } else if ((activity as MainActivity).modelo.eventos[id].tipo == "CICLO DE CINE"){
            pos = 1
        } else {
            pos = 2
        }
        tipo.setSelection(pos)
        val descripcion = binding.editDescripcionInput
        descripcion.setText((activity as MainActivity).modelo.eventos[id].descripcion)
        val lugar = binding.editLugarInput
        lugar.setText((activity as MainActivity).modelo.eventos[id].lugar)
        val ciudad = binding.editCiudadInput
        ciudad.setText((activity as MainActivity).modelo.eventos[id].ciudad)
        val fechaI = binding.editFechaInput
        fechaI.setText((activity as MainActivity).modelo.eventos[id].fechaInicio)
        val fechaF = binding.editFechafinInput
        fechaF.setText((activity as MainActivity).modelo.eventos[id].fechaFin)
        val url = binding.editUrlInput
        url.setText((activity as MainActivity).modelo.eventos[id].foto)



        val botonGuardar = binding.guardarEditarEvento


        botonGuardar.setOnClickListener {
            var titulo = binding.editTituloInput.text.toString()
            var tipo = binding.editTipoSelect.selectedItem.toString()
            var descripcion = binding.editDescripcionInput.text.toString()
            var lugar = binding.editLugarInput.text.toString()
            var ciudad = binding.editCiudadInput.text.toString()
            var fechaInicio = binding.editFechaInput.text.toString()
            var fechaFin = binding.editFechafinInput.text.toString()
            var imagen = binding.editUrlInput.text.toString()
            if (!titulo.equals("") && !tipo.equals("") && !lugar.equals("") && !ciudad.equals("") && !fechaInicio.equals("")){
                (activity as MainActivity).modelo.editEvent(id, titulo, tipo, descripcion, lugar, ciudad, fechaInicio, fechaFin, imagen)
                findNavController().navigate(com.example.kulturklub.R.id.action_fifthFragment_to_thirdFragment)
            }
        }

        val botonCancelar = binding.cancelbutton

        botonCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_fifthFragment_to_thirdFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}