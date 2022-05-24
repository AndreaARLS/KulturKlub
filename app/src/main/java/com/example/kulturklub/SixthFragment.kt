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
    var eventid = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSixthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventid = arguments?.getInt("id") ?:-1

        setHasOptionsMenu(true)
        activity?.title= "Detalles de evento"

        val titulo = binding.detalleNombre
        titulo.setText((activity as MainActivity).modelo.eventos[eventid].titulo)
        val tipo = binding.detalleTipo
        tipo.setText((activity as MainActivity).modelo.eventos[eventid].tipo)
        val lugar = binding.detalleLugarCiudad
        lugar.setText((activity as MainActivity).modelo.eventos[eventid].lugar + " (" + (activity as MainActivity).modelo.eventos[eventid].lugar +")" )
        val fechas = binding.detalleFechas
        if ((activity as MainActivity).modelo.eventos[eventid].fechaFin.equals("")){
            fechas.setText((activity as MainActivity).modelo.eventos[eventid].fechaInicio)
        } else {
            fechas.setText((activity as MainActivity).modelo.eventos[eventid].fechaInicio + " - " + (activity as MainActivity).modelo.eventos[eventid].fechaFin)
        }
        val descripcion = binding.detalleDescripcion
        descripcion.setText((activity as MainActivity).modelo.eventos[eventid].descripcion)
        val imagen = binding.imageEvento
        Glide.with(this).load((activity as MainActivity).modelo.eventos[eventid].foto).into(imagen)



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
        val bundle2 = bundleOf("id" to ((activity as MainActivity).modelo.eventos[eventid].creador - 1))
        when (item.itemId) {
            (activity as MainActivity).usermenu -> findNavController().navigate(R.id.action_sixthFragment_to_seventhFragment, bundle1)
            (activity as MainActivity).creatormenu -> {
                Log.i(TAG, "Andrewww" + ((activity as MainActivity).modelo.eventos[eventid].creador).toString())
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