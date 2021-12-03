package com.example.inertia.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.inertia.R
import com.example.inertia.room.DatabaseConfig
import com.example.inertia.room.entity.UsersEntity

class UserSettings : Fragment() {
    private lateinit var databaseConfig: DatabaseConfig

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_settings, container, false)
        databaseConfig = DatabaseConfig.databaseGetInstance(requireActivity())!!

//        val springCard = view.findViewById<MaterialCardView>(R.id.springLayout)
//        springCard.setOnClickListener {
//            SpringAnimation(view, DynamicAnimation.TRANSLATION_Y,1200f)
//            start()
//        }
//        val backButton = view.findViewById<ImageView>(R.id.backButtonSettings)

        val id: Int = databaseConfig.usersDao()!!.getLastId()
        val userText = view.findViewById<TextView>(R.id.userNameSettings)
        val helloText = "Hello"

        if (databaseConfig.usersDao()?.getData(id) == true){
            val usersEntity: UsersEntity =  databaseConfig.usersDao()!!.getName(id)
            userText.text = "$helloText ${usersEntity.name}"
        }
        return view
    }
}