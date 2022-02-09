package com.pawan.jetpackandroidapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pawan.jetpackandroidapp.R
import com.pawan.jetpackandroidapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding= FragmentHomeBinding.bind(view)

        binding.btnOnline.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_onlineFragment)
        }

        binding.btnOffline.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_offLineFragment)
        }
    }
}