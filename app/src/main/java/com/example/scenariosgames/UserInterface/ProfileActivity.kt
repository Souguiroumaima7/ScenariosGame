package com.example.scenariosgames.UserInterface

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class ProfileActivity : ComponentActivity() {
    private lateinit var nextbutton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
               nextbutton.setOnClickListener {
                       val intent = Intent(this, DetailsGame::class.java)
                    startActivity(intent)
                   finish()
               }
            }
        }


