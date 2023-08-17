package com.example.scenariosgames.userinterface



import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R
import com.example.scenariosgames.creationGame.GameActivity

class ExploreGame : ComponentActivity() {
   private lateinit var nexting:Button
   private lateinit var downbutton:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exploregame)
           nexting.setOnClickListener {
               val intent = Intent(this,GameActivity::class.java)
               startActivity(intent)
           }

          downbutton.setOnClickListener {
            Toast.makeText(this, "ImageButton clicked", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_home -> {
                // Handle settings button click
                showToast("Home clicked")
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

