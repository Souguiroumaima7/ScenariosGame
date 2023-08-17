package com.example.scenariosgames.userinterface

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.creationGame.GameActivity
import com.example.scenariosgames.R


class MainActivity : AppCompatActivity() {
    private lateinit var secretcode:EditText
    private lateinit var startinggame:Button
    private lateinit var Clickhere:Button
    private lateinit var clickme:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

              startinggame = findViewById(R.id.startgame)
              Clickhere = findViewById(R.id.home)
              clickme = findViewById(R.id.buttonvide)


             startinggame.setOnClickListener {

                  val intent = Intent(this,GameActivity::class.java)
                 startActivity(intent)

             }
             Clickhere.setOnClickListener {
                 val intent = Intent(this,HomeActivity::class.java)
                 startActivity(intent)
             }
           clickme.setOnClickListener {
               handleButtonClick()
           }

        }

    private fun handleButtonClick() {
        println("le button est click")
    }

}



