package com.example.scenariosgames.userinterface

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R

class HomeActivity : ComponentActivity() {
    private lateinit var profileimage: ImageView
    private lateinit var newyorkimage: ImageView

    private lateinit var locationtext:TextView
    private lateinit var paristext:TextView
    private lateinit var updownbutton: Button
    private lateinit var arrowforward: Button
    private lateinit var sharebutton: Button
    private lateinit var readtos: Button
    private lateinit var confidentialitybutton: Button
    private lateinit var readmorebutton: Button
    private lateinit var doesabout:TextView
    private lateinit var about:TextView
    private lateinit var descriptiontext:TextView
    private lateinit var readmore:TextView
    private val cities = listOf("New York", "paris","Los Angeles", "Chicago", "Houston", "Miami")

    private var currentCityIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        profileimage = findViewById(R.id.profile)
        newyorkimage = findViewById(R.id.newyork)
        locationtext = findViewById(R.id.location)
        paristext = findViewById(R.id.paris)

        doesabout = findViewById(R.id.Howdoesitwork)
        about = findViewById(R.id.aboutus)
        descriptiontext = findViewById(R.id.textView4)
        readmore = findViewById(R.id.readmore)

        updateCityTextView()

        updownbutton.setOnClickListener {
            currentCityIndex = (currentCityIndex + 1) % cities.size
            updateCityTextView()
        }
        arrowforward.setOnClickListener {
            val intent = Intent(this, DetailsGame::class.java).setAction("your.custom.action")
            startActivity(intent)
        }
        sharebutton.setOnClickListener {
            handleButtonClick()
        }
        readtos.setOnClickListener {
            handleButtonClick()
        }
        confidentialitybutton.setOnClickListener {
            handleButtonClick()
        }

        readmorebutton.setOnClickListener {
            val intent = Intent(this, ExploreGame::class.java)
            startActivity(intent)
        }
    }


    private fun updateCityTextView() {
        val currentCity = cities[currentCityIndex]
        locationtext.text = currentCity
    }

    private fun handleButtonClick() {
       val doesabout = doesabout.text
        doesabout.toString()
        val about = about.text
        about.toString()
        val description = descriptiontext.contentDescription
        description.toString()
        val readmore = readmore.text
        readmore.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_game -> {
                // Handle settings button click
                showToast("game clicked")
                true
            }

            R.id.action_exploregame -> {
                // Handle about button click
                showToast("explore game clicked")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}