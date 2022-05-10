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

        val id: Int = arguments?.getInt("id") ?:-1

        setHasOptionsMenu(true)
        activity?.title= "Detalles de usuario"

        val username = binding.userNombre
        username.setText((activity as MainActivity).modelo.usuarios[id].username)
        val email = binding.userEmail
        email.setText((activity as MainActivity).modelo.usuarios[id].email)
        val avatar = binding.imageUsuario
        Glide.with(this).load((activity as MainActivity).modelo.usuarios[id].avatar).into(avatar)


        binding.editIcon4.setOnClickListener(){
            val bundle = bundleOf("id" to id)
            this.findNavController().navigate(R.id.action_seventhFragment_to_eighthFragment, bundle)
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