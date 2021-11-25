package com.example.inertia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.inertia.Dashboard.BuildFragment
import com.example.inertia.Dashboard.explore.ExploreFragment
import com.example.inertia.Dashboard.TrainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val buildFragment = BuildFragment()
        val exploreFragment = ExploreFragment()
        val trainFragment = TrainFragment()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        makeCurrentFragment(exploreFragment)
        supportFragmentManager.beginTransaction()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.explore -> makeCurrentFragment(exploreFragment)
                R.id.train -> makeCurrentFragment(trainFragment)
                R.id.build -> makeCurrentFragment(buildFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}