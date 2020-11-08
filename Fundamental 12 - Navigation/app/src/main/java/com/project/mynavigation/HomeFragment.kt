package com.project.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    //TODO 2: buat sebuah fragment yang berfungsi sebagai startDestination (tujuan awal) dari Navigation Graph
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    //TODO 10: buat on view, terus kasih on click listener
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_category.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_categoryFragment)
        )
        btn_profile.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_profileActivity)
        }
    }
}