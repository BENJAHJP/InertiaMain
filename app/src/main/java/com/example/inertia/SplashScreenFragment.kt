package com.example.inertia

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        Handler().postDelayed({
            if(onBoardingFinished()){
                findNavController().navigate(R.id.action_splashScreenFragment_to_dashboardFragment)
            }else{
                findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardingFragment)
            }
        },3000)

        val textView = view.findViewById<TextView>(R.id.splashText)
        val imageView = view.findViewById<ImageView>(R.id.splashImage)
        val topAnimation = AnimationUtils.loadAnimation(context,R.anim.top_animation)
        val bottomAnimation = AnimationUtils.loadAnimation(context, R.anim.bottom_animation)

        textView.startAnimation(topAnimation)
        imageView.startAnimation(bottomAnimation)

        return view
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}