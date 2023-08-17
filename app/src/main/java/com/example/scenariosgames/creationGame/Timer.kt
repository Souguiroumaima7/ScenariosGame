package com.example.scenariosgames.creationGame


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.R

class Timer : AppCompatActivity() {
    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jeux)

        // Commencer la partie
        startGame()
        endGame()
    } 

    private fun startGame() {
        startTime = System.currentTimeMillis()
        // Commencer le jeu
    }

    private fun endGame() {
        val endTime = System.currentTimeMillis()
        val gameDurationMillis = endTime - startTime
        val gameDurationSeconds = gameDurationMillis / 1000.0

        // Afficher ou utiliser la durée de la partie
        println("Durée de la partie : $gameDurationSeconds secondes")
    }
}
