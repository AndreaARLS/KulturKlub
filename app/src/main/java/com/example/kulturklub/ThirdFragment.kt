package com.example.kulturklub
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kulturklub.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    lateinit var miRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        miRecyclerView = binding.recycler
        miRecyclerView.layoutManager = LinearLayoutManager(activity)

        miRecyclerView.adapter = Adaptador(this, (activity as MainActivity).modelo.eventos, activity as MainActivity)

        setHasOptionsMenu(true)
        activity?.title= "Próximos eventos"

        binding.nuevo.setOnClickListener(){
            findNavController().navigate(R.id.action_thirdFragment_to_forthFragment)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem((activity as MainActivity).usermenu)?.isVisible = true
        menu.findItem((activity as MainActivity).creatormenu)?.isVisible = false
        menu.findItem((activity as MainActivity).logoutmenu)?.isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle1 = bundleOf("id" to (activity as MainActivity).currentUser)
        when (item.itemId) {
            (activity as MainActivity).usermenu -> findNavController().navigate(R.id.action_thirdFragment_to_seventhFragment, bundle1)
            (activity as MainActivity).creatormenu -> false
            (activity as MainActivity).logoutmenu -> findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
        }
        return super.onOptionsItemSelected(item)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    public fun rellenarRecycler(view: View){
        miRecyclerView = binding.recycler
        miRecyclerView.layoutManager = LinearLayoutManager(activity)

        miRecyclerView.adapter = Adaptador(this, (activity as MainActivity).modelo.eventos, activity as MainActivity)

    }
}