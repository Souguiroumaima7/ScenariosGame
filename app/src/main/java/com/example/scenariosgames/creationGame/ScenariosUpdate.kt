package com.example.scenariosgames.creationGame


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R

class ScenariosUpdate : ComponentActivity() {
private lateinit var imageparis:ImageView
private lateinit var editview:Button
private lateinit var deleteview:Button
private lateinit var time:Button
private lateinit var ajout:Button
private lateinit var savebutton:Button
private lateinit var scenarios: TextView
private lateinit var hourtext:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_scenarios)

        imageparis = findViewById(R.id.imageView7)
        time = findViewById(R.id.imageView9)
        editview = findViewById(R.id.edit1)
        deleteview = findViewById(R.id.delete1)
        hourtext = findViewById(R.id.hour1)
        editview.setOnClickListener {
            handleButtonClick()
        }
        deleteview.setOnClickListener {
            handleButtonClick()
        }
        ajout.setOnClickListener {
            handleButtonClick()
        }
        savebutton.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)

        }
    }

    private fun handleButtonClick() {
        println("le button est click")
        val Scenraios = scenarios.text
        Scenraios.toString()

    }

}


