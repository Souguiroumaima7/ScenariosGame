package com.example.scenariosgames.UserInterface

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosapplication.UserInterface.HomeActivity
import com.example.scenariosgames.CreationGame.GameActivity
import com.example.scenariosgames.R


class MainActivity : AppCompatActivity() {

     private lateinit var Startgame:Button
     private lateinit var homepage:Button
     private lateinit var textViewEnterCode:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
               Startgame.setOnClickListener {
                  val entercode = textViewEnterCode.text.toString()
                  val intent = Intent(this, GameActivity::class.java)
                   startActivity(intent)
                   finish()
               }
        homepage.setOnClickListener{
                   val intent = Intent(this,ProfileActivity::class.java)
                  startActivity(intent)
            finish()
        }
        }

    

    }



