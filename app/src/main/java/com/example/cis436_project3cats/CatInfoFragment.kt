package com.example.cis436_project3cats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.cis436_project3cats.databinding.FragmentCatInfoBinding


class CatInfoFragment : Fragment() {
    private lateinit var binding: FragmentCatInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCatInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun updateTextDesc (cat : CatInfo){

        binding.tVName.text = "Name: ${cat.name}"
        binding.tVOrigin.text = "Origin: ${cat.origin}"
        binding.tVTemper.text = "Temperament: ${cat.temperament}"
        binding.tvCatDesc.text = "Description: ${cat.description}"


        //using glide to handle image loading into image view
        Glide.with(this).load(cat.imageURL).into(binding.iVCat)




    }



}//end of fragment