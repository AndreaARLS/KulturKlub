package com.example.kulturklub
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.kulturklub.databinding.FragmentEighthBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EighthFragment : Fragment() {

    private var _binding: FragmentEighthBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEighthBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: String = arguments?.getString("id") ?: ""

        setHasOptionsMenu(true)
        activity?.title= "Editar usuario"

        var usuarios = (activity as MainActivity).modelo.consultarUsuarios()
        lateinit var usu : Usuario
        for (u in usuarios){
            if (u.id == id){
                usu = u
            }
        }

        val name = binding.editUsernameInput
        name.setText(usu.username)
        val email = binding.editEmailInput
        email.setText(usu.email)
        val pwd = binding.editPasswordInput
        pwd.setText(usu.password)
        val img = binding.editUrlInput
        img.setText(usu.avatar)

        val botonGuardar = binding.guardarEditarEvento

        botonGuardar.setOnClickListener {
            var name = binding.editUsernameInput.text.toString()
            var email = binding.editEmailInput.text.toString()
            var pwd = binding.editPasswordInput.text.toString()
            var pass = (activity as MainActivity).hashString(pwd)
            var img = binding.editUrlInput.text.toString()
            if (!name.equals("") && !email.equals("") && !pwd.equals("")){
                (activity as MainActivity).modelo.editUser(id, name, email,pass,img, activity as MainActivity)
                val bundle = bundleOf("id" to id)
                findNavController().navigate(R.id.action_eighthFragment_to_seventhFragment, bundle)
            }
        }

        val botonCancelar = binding.cancelbutton

        botonCancelar.setOnClickListener {
            val bundle = bundleOf("id" to id)
            findNavController().navigate(R.id.action_eighthFragment_to_seventhFragment, bundle)
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
            (activity as MainActivity).logoutmenu -> {
                (activity as MainActivity).currentUser = ""
                findNavController().navigate(R.id.action_eighthFragment_to_FirstFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}