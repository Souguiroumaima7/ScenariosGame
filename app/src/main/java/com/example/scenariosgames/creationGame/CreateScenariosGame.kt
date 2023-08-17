package com.example.scenariosgames.creationGame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R


class CreateScenariosGame: ComponentActivity() {
    private lateinit var uploadpicture:Button
    private lateinit var uploadaudiovideo:Button
    private lateinit var PlayerEdit:EditText
    private lateinit var TimerEdit:EditText
    private lateinit var instructionEditText: EditText
    private lateinit var SaveButton:Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_create_scenarios)
        uploadpicture = findViewById(R.id.uploadPicture)
        uploadaudiovideo = findViewById(R.id.videos)
        PlayerEdit = findViewById(R.id.PlayerseditText)
        TimerEdit = findViewById(R.id.TimerEditText)
        instructionEditText = findViewById(R.id.editTextTextMultiLine2)

        uploadaudiovideo.setOnClickListener {
            openVideoPicker()
        }
        SaveButton.setOnClickListener {
            val intent = Intent(this,ScenariosUpdate::class.java)
            startActivity(intent)
            finish()
        }
        uploadpicture.setOnClickListener {
            openImagePicker();
        }
        }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
    }

    private fun openVideoPicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "video/*"
    }

}


