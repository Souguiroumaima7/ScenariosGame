package com.example.scenariosapplication.UserInterface

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.CreationGame.GameActivity

import com.example.scenariosgames.R
import com.example.scenariosgames.UserInterface.ExploreGame


class HomeActivity : ComponentActivity() {
    private lateinit var nextbutton:Button
    private lateinit var readmore:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
            nextbutton.setOnClickListener{
                val intent = Intent(this , ExploreGame::class.java )
                startActivity(intent)
                finish()
            }
          readmore.setOnClickListener {
              val intent = Intent(this, GameActivity ::class.java)
              startActivity(intent)
              finish()
          }
    }
}

