package com.example.kulturklub

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kulturklub.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        activity?.title= "Kultur Klub"


        binding.registerButton.setOnClickListener {
            var name = binding.usernameInput.text.toString()
            var avatar = binding.imageInput.text.toString()
            var email = binding.emailInput.text.toString()
            var pwd = binding.passwordInput.text.toString()
            var pass = (activity as MainActivity).hashString(pwd)
            if (!name.equals("") && !email.equals("") && !pwd.equals("")){
                (activity as MainActivity).modelo.nuevoUser(name, email, pass, avatar, (activity as MainActivity))
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }

        binding.loginLink.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
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