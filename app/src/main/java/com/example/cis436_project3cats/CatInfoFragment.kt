package com.example.cis436_project3cats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cis436_project3cats.databinding.FragmentCatInfoBinding


class CatInfoFragment : Fragment() {
    private lateinit var binding: FragmentCatInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCatInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: need function to retrieve cat img and display it
    //TODO: need function to retrieve cat data and display it


}//end of fragment