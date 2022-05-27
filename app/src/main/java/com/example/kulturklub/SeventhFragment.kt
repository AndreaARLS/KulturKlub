package com.example.kulturklub

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kulturklub.databinding.FragmentSevenBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SeventhFragment : Fragment() {

    private var _binding: FragmentSevenBinding? = null
    var idUser = -1

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSevenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = arguments?.getString("id") ?: ""

        setHasOptionsMenu(true)
        activity?.title= "Detalles de usuario"

        var usuarios = (activity as MainActivity).modelo.consultarUsuarios()
        lateinit var usu : Usuario
        for (u in usuarios){
            if (u.id == id){
                usu = u
            }
        }

        val username = binding.userNombre
        username.setText(usu.username)
        val email = binding.userEmail
        email.setText(usu.email)
        val avatar = binding.imageUsuario
        Glide.with(this).load(usu.avatar).into(avatar)


        binding.editIcon4.setOnClickListener(){
            val bundle = bundleOf("id" to id)
            this.findNavController().navigate(R.id.action_seventhFragment_to_eighthFragment, bundle)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem((activity as MainActivity).home)?.isVisible = true
        menu.findItem((activity as MainActivity).usermenu)?.isVisible = true
        menu.findItem((activity as MainActivity).creatormenu)?.isVisible = false
        menu.findItem((activity as MainActivity).logoutmenu)?.isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle1 = bundleOf("id" to (activity as MainActivity).currentUser)
        when (item.itemId) {
            (activity as MainActivity).home -> findNavController().navigate(R.id.action_seventhFragment_to_thirdFragment)
            (activity as MainActivity).usermenu -> findNavController().navigate(R.id.action_seventhFragment_self, bundle1)
            (activity as MainActivity).logoutmenu -> findNavController().navigate(R.id.action_seventhFragment_to_FirstFragment)
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}