package com.example.inertia.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.inertia.R

class Screen3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen3, container, false)

        val finish = view.findViewById<TextView>(R.id.finish)

        finish.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_usersInfo)
        }
        return view
    }
}