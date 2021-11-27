package com.example.inertia.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.inertia.Dashboard.explore.ExploreFragment
import com.example.inertia.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val fragmentManager = requireActivity().supportFragmentManager
        //val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val buildFragment = BuildFragment()
        val exploreFragment = ExploreFragment()
        val trainFragment = TrainFragment()
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigation)

        makeCurrentFragment(exploreFragment)
        fragmentManager.beginTransaction()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.explore -> makeCurrentFragment(exploreFragment)
                R.id.train -> makeCurrentFragment(trainFragment)
                R.id.build -> makeCurrentFragment(buildFragment)
            }
            true
        }
        return view
    }
    private fun makeCurrentFragment(fragment: Fragment) =
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }