package com.example.inertia

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if(onBoardingFinished()){
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_dashboardFragment)
                }
            }else{
                findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardingFragment)
            }
        },5000)
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    private fun onBoardingFinished(): Boolean{
        val activity: FragmentActivity? = activity
        return if (activity !== null){
            val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
            sharedPref.getBoolean("Finished", false)
        }else{
            true
        }
    }
}