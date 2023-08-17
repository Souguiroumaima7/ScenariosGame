package com.example.scenariosgames.userinterface

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R


class ProfileActivity : ComponentActivity() {
    private lateinit var nextbutton:Button
    private lateinit var editprofile:Button
    private lateinit var retour:Button
    private lateinit var Profile:TextView
    private lateinit var JackFarst:TextView
    private lateinit var profileimage:ImageView
     private  lateinit var finshedgame:TextView
     private lateinit var imageView17:ImageView
     private lateinit var TheTuilriesQuest:TextView
     private lateinit var AnimaAgentLudique1:TextView
     private lateinit var imageView23:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         nextbutton = findViewById(R.id.next)
          editprofile = findViewById(R.id.editprofile)
          retour = findViewById(R.id.backward)
           Profile = findViewById(R.id.Profile)
        JackFarst = findViewById(R.id.JackFarst)
        profileimage = findViewById(R.id.imageView24)
        finshedgame = findViewById(R.id.finshedgame)
        imageView17 = findViewById(R.id.imageView17)
        TheTuilriesQuest = findViewById(R.id.TheTuilriesQuest)
        AnimaAgentLudique1 = findViewById(R.id.AnimaAgentLudique1)
        imageView23 = findViewById(R.id.imageView23)

               nextbutton.setOnClickListener {
                       val intent = Intent(this, DetailsGame::class.java)
                    startActivity(intent)
               }

        editprofile.setOnClickListener {
            val intent = Intent(this , EditProfile::class.java )
            startActivity(intent)
        }
        retour.setOnClickListener {
            val intent = Intent(this , HomeActivity::class.java )
            startActivity(intent)
        }
            }
        }


