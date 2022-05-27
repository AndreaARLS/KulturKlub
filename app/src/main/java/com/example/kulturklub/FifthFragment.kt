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
    lateinit var evento : Evento


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var position = arguments?.getInt("id")?: -1
        val eventos : MutableList<Evento> = (activity as MainActivity).modelo.consultarEventos()
        evento = eventos[position]
        eventid = evento.id

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
        titulo.setText(evento.titulo)
        val tipo = binding.editTipoSelect
        var pos = 0
        if (evento.tipo == "CONCIERTO") {
            pos = 0
        } else if (evento.tipo == "CICLO DE CINE"){
            pos = 1
        } else {
            pos = 2
        }
        tipo.setSelection(pos)
        val descripcion = binding.editDescripcionInput
        descripcion.setText(evento.descripcion)
        val lugar = binding.editLugarInput
        lugar.setText(evento.lugar)
        val ciudad = binding.editCiudadInput
        ciudad.setText(evento.ciudad)
        val fechaI = binding.editFechaInput
        fechaI.setText(evento.fechaInicio)
        val fechaF = binding.editFechafinInput
        fechaF.setText(evento.fechaFin)
        val url = binding.editUrlInput
        url.setText(evento.foto)



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
                (activity as MainActivity).modelo.editEvent(evento.id, titulo, tipo, descripcion, lugar, ciudad, fechaInicio, fechaFin, imagen, activity as MainActivity)
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
        menu.findItem((activity as MainActivity).home)?.isVisible = false
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