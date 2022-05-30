package com.example.kulturklub

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kulturklub.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        activity?.title= "Kultur Klub"

        binding.loginButton.setOnClickListener {
            var email = binding.emailInput.text.toString()
            var pwd = binding.passwordInput.text.toString()
            var pass = (activity as MainActivity).hashString(pwd)
            var usuarios = (activity as MainActivity).modelo.consultarUsuarios()
            var encontrado : Boolean = false
            for (u in usuarios){
                if (u.email==email && u.password==pass){
                    encontrado = true
                    (activity as MainActivity).currentUser = u.id
                    findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
                    Toast.makeText(activity as MainActivity, "HOLA " + u.username, Toast.LENGTH_LONG).show()
                    binding.emailInput.setText("")
                    binding.passwordInput.setText("")
                }
            }
            if (encontrado == false){
                binding.emailInput.setText("")
                binding.passwordInput.setText("")
                Toast.makeText(activity as MainActivity, "Usuario o contraseña erróneos", Toast.LENGTH_LONG).show()
            }
        }


        binding.registerLink.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem((activity as MainActivity).home)?.isVisible = false
        menu.findItem((activity as MainActivity).usermenu)?.isVisible = false
        menu.findItem((activity as MainActivity).creatormenu)?.isVisible = false
        menu.findItem((activity as MainActivity).logoutmenu)?.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}