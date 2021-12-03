package com.example.inertia.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.inertia.dashboard.explore.ExploreFragment
import com.example.inertia.R
import com.example.inertia.room.DatabaseConfig
import com.example.inertia.room.entity.UsersEntity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardFragment : Fragment() {
    private lateinit var databaseConfig: DatabaseConfig
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val fragmentManager = requireActivity().supportFragmentManager

        val buildFragment = BuildFragment()
        val exploreFragment = ExploreFragment()
        val trainFragment = TrainFragment()
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val userText = view.findViewById<TextView>(R.id.userName)
        val settingsButton = view.findViewById<ImageView>(R.id.userSettings)

        settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_userSettings2)
        }

        databaseConfig = DatabaseConfig.databaseGetInstance(requireActivity())!!
        val id: Int = databaseConfig.usersDao()?.getLastId()!!

        if (databaseConfig.usersDao()?.getData(id) == true){
            val usersEntity: UsersEntity =  databaseConfig.usersDao()!!.getName(id)
            userText.text = usersEntity.name
        }

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