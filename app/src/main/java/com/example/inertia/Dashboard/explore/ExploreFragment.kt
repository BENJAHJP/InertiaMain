package com.example.inertia.Dashboard.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.inertia.R
import com.google.android.material.card.MaterialCardView

class ExploreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        val textRecognitionCard = view.findViewById<MaterialCardView>(R.id.textRecognitionCard)

        textRecognitionCard.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment2_to_imageUpload)
        }
        return view
    }
}