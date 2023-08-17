package com.example.scenariosgames.creationGame


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R

class ScenariosUpdate : ComponentActivity() {
private lateinit var imageparis:ImageView
private lateinit var editview:ImageButton
private lateinit var deleteview:ImageButton
private lateinit var time:ImageView
private lateinit var ajout:ImageButton
private lateinit var savebutton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_scenarios)

        imageparis = findViewById(R.id.imageView7)
        time = findViewById(R.id.imageView9)

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
    }

}


