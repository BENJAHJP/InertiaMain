package com.example.inertia.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.inertia.R
import com.example.inertia.Room.DatabaseConfig
import com.example.inertia.Room.Entity.UsersEntity

class UserSettings : Fragment() {
    lateinit var databaseConfig: DatabaseConfig
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_settings, container, false)
        databaseConfig = DatabaseConfig.databaseGetInstance(requireActivity())!!

        val id: Int = databaseConfig.usersDao()!!.getLastId()
        var userText = view.findViewById<TextView>(R.id.userNameSettings)
        val helloText: String = "Hello"

        if (databaseConfig.usersDao()?.getData(id) == true){
            val usersEntity: UsersEntity =  databaseConfig.usersDao()!!.getName(id)
            userText.text = "$helloText usersEntity.name"
        }
        return view
    }
}