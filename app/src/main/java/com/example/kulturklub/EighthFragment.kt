package com.example.kulturklub
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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

        val id: Int = arguments?.getInt("id") ?:-1

        setHasOptionsMenu(true)
        activity?.title= "Editar usuario"


        val name = binding.editUsernameInput
        name.setText((activity as MainActivity).modelo.usuarios[id].username)
        val email = binding.editEmailInput
        email.setText((activity as MainActivity).modelo.usuarios[id].email)
        val pwd = binding.editPasswordInput
        pwd.setText((activity as MainActivity).modelo.usuarios[id].password)
        val img = binding.editUrlInput
        img.setText((activity as MainActivity).modelo.usuarios[id].avatar)



        val botonGuardar = binding.guardarEditarEvento


        botonGuardar.setOnClickListener {
            var name = binding.editUsernameInput.text.toString()
            var email = binding.editEmailInput.text.toString()
            var pwd = binding.editPasswordInput.text.toString()
            var img = binding.editUrlInput.text.toString()

            if (!name.equals("") && !email.equals("") && !pwd.equals("")){
                (activity as MainActivity).modelo.editUser(id, name, email,pwd,img)
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
        menu.findItem(R.id.miUsuario)?.isVisible = false
        menu.findItem(R.id.organizador)?.isVisible = false
        menu.findItem(R.id.logout)?.isVisible = true
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}