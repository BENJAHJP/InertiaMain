package com.example.inertia.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.inertia.MainActivity2
import com.example.inertia.R

class Screen3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen3, container, false)

        val finish = view.findViewById<TextView>(R.id.finish)

        finish.setOnClickListener {
            val intent = Intent (activity, MainActivity2::class.java)
            activity?.startActivity(intent)
            onBoardingFinished()
        }
        return view
    }
    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}