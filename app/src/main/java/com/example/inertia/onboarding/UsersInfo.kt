package com.example.inertia.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.inertia.R
import com.example.inertia.Room.DatabaseConfig
import com.example.inertia.Room.Entity.UsersEntity
import com.google.android.material.button.MaterialButton

class UsersInfo : Fragment() {
    private lateinit var databaseConfig: DatabaseConfig
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users_info, container, false)
        val userNameInput = view.findViewById<EditText>(R.id.userNameInput)
        val ageInput = view.findViewById<EditText>(R.id.ageInput)
        val submitButton = view.findViewById<MaterialButton>(R.id.submitButton)

        databaseConfig = DatabaseConfig.databaseGetInstance(requireActivity())!!

        submitButton.setOnClickListener {
            if (userNameInput.text.isEmpty() || ageInput.text.isEmpty()){
                Toast.makeText(activity,"Please enter missing details", Toast.LENGTH_LONG).show()
            }else{
                databaseConfig.usersDao()?.insert(usersEntity = UsersEntity(userNameInput.text.toString(),ageInput.text.toString()))
                findNavController().navigate(R.id.action_usersInfo_to_dashboardFragment)
                onBoardingFinished()
            }
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