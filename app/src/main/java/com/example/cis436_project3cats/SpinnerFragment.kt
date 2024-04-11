package com.example.cis436_project3cats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.cis436_project3cats.databinding.FragmentSpinnerBinding

class SpinnerFragment : Fragment() {
    private lateinit var binding : FragmentSpinnerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpinnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Add cat names to spinner
    fun setSpinner(catArray: MutableList<String>) {
        val adapter = activity?.let {ArrayAdapter(it, android.R.layout.simple_spinner_item, catArray) }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinCats.adapter = adapter
    }

}