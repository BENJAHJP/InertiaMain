package com.example.inertia.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.inertia.R

class Screen1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen1, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        val next = view.findViewById<TextView>(R.id.nextScreen1)

        next.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }
}