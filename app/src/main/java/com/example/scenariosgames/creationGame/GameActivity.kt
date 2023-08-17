package com.example.scenariosgames.creationGame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R
import com.example.scenariosgames.userinterface.ExploreGame

class GameActivity: ComponentActivity() {
    private lateinit var imageback:ImageButton
    private lateinit var uploadpicture:Button
    private lateinit var creategame:TextView
    private lateinit var gametype:TextView
    private lateinit var difficulty:TextView
    private lateinit var noofplayers:TextView
    private  lateinit var gamecost:TextView
    private lateinit var adventurebutton:Button
    private lateinit var explorationButtton:Button
    private lateinit var engimaButton:Button
    private lateinit var facileButton:Button
    private lateinit var normaleButton:Button
    private lateinit var difficileButton:Button
    private lateinit var mikeplayer:EditText
    private lateinit var amirplayer:EditText
    private lateinit var cost:EditText
   private lateinit var nextbutton:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jeux)

        imageback = findViewById(R.id.imageView5)
        imageback.setImageResource(R.drawable.navigation_back_arrow_svgrepo_com)
        creategame = findViewById(R.id.createagame)
        gametype = findViewById(R.id.gametype)
        difficulty = findViewById(R.id.Difficulty)
        noofplayers = findViewById(R.id.NoOfPlayers)
        gamecost = findViewById(R.id.Gamecost)
        mikeplayer = findViewById(R.id.Amir)
        amirplayer = findViewById(R.id.Amir)
        cost = findViewById(R.id.Gamecost)


        uploadpicture.setOnClickListener {
            openImagePicker();
        }
        adventurebutton.setOnClickListener {
            handleButtonClick()
        }
        explorationButtton.setOnClickListener {
            handleButtonClick()
        }
        engimaButton.setOnClickListener {
            handleButtonClick()
        }
        facileButton.setOnClickListener {
            handleButtonClick()
        }
        normaleButton.setOnClickListener {
            handleButtonClick()
        }
        difficileButton.setOnClickListener {
            handleButtonClick()
        }
        nextbutton.setOnClickListener {
            val intent = Intent(this, CreateScenariosGame::class.java)
            startActivity(intent)
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
    }

    private fun handleButtonClick() {
        println("Le bouton a été cliqué!")
    }

}




