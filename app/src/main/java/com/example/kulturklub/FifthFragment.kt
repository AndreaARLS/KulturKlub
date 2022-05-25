package com.example.kulturklub
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.kulturklub.databinding.FragmentFifthBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FifthFragment : Fragment() {

    private var _binding: FragmentFifthBinding? = null
    private val binding get() = _binding!!

    var eventid : String = ""
    var evento : MutableList<String> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFifthBinding.inflate(inflater, container, false)
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
        titulo.setText(evento[1])
        val tipo = binding.editTipoSelect
        var pos = 0
        if (evento[2] == "CONCIERTO") {
            pos = 0
        } else if (evento[2] == "CICLO DE CINE"){
            pos = 1
        } else {
            pos = 2
        }
        tipo.setSelection(pos)
        val descripcion = binding.editDescripcionInput
        descripcion.setText(evento[3])
        val lugar = binding.editLugarInput
        lugar.setText(evento[4])
        val ciudad = binding.editCiudadInput
        ciudad.setText(evento[5])
        val fechaI = binding.editFechaInput
        fechaI.setText(evento[6])
        val fechaF = binding.editFechafinInput
        fechaF.setText(evento[7])
        val url = binding.editUrlInput
        url.setText(evento[8])



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
                (activity as MainActivity).modelo.editEvent(evento[0], titulo, tipo, descripcion, lugar, ciudad, fechaInicio, fechaFin, imagen, activity as MainActivity)
                findNavController().navigate(com.example.kulturklub.R.id.action_fifthFragment_to_thirdFragment)
            }
        }

        val botonCancelar = binding.cancelbutton

        botonCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_fifthFragment_to_thirdFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem((activity as MainActivity).usermenu)?.isVisible = false
        menu.findItem((activity as MainActivity).creatormenu)?.isVisible = false
        menu.findItem((activity as MainActivity).logoutmenu)?.isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            (activity as MainActivity).logoutmenu -> findNavController().navigate(R.id.action_fifthFragment_to_FirstFragment)
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}