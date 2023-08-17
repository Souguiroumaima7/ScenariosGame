package com.example.scenariosgames.userinterface

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity


class ProfileActivity : ComponentActivity() {
    private lateinit var nextbutton:Button
    private lateinit var editprofile:Button
    private lateinit var retour:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
               nextbutton.setOnClickListener {
                       val intent = Intent(this, DetailsGame::class.java)
                    startActivity(intent)
               }

        editprofile.setOnClickListener {
            val intent = Intent(this , ProfileActivity::class.java )
            startActivity(intent)
        }
        retour.setOnClickListener {
            val intent = Intent(this , HomeActivity::class.java )
            startActivity(intent)
        }
            }
        }


