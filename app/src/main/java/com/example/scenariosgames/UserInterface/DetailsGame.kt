package com.example.scenariosgames.UserInterface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.R
import java.util.Timer

class DetailsGame(
    val description: String,
    val requirements: String,
    val exploration: String,
    var uplevel: Int, var hours: Int) : ComponentActivity() {

    class Player(var name: String, var currentGame: DetailsGame)


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_detailsjeux)

            val game1 = DetailsGame(
                description = "Un jeu d'aventure passionnant dans l'espace",
                requirements = "Configuration requise : processeur 2.0 GHz, 8 Go de RAM",
                exploration = "Explorez des planètes inconnues et découvrez des mystères cosmiques",
                uplevel = 5, // Niveau actuel du joueur
                hours = 20 // Heures de jeu jusqu'à présent
            )

            val game2 = DetailsGame(
                description = "Un jeu de rôle épique avec des combats stratégiques",
                requirements = "Configuration requise : processeur 2.5 GHz, 16 Go de RAM",
                exploration = "Parcourez un monde ouvert riche et varié",
                uplevel = 10,
                hours = 40

            )

            val player = Player("Alice", game1)

            println("Bienvenue, ${player.name} !")
            println("Description du jeu : ${player.currentGame.description}")
            println("Configuration requise : ${player.currentGame.requirements}")
            println("Exploration : ${player.currentGame.exploration}")
            println("Niveau actuel : ${player.currentGame.uplevel}")
            println("Heures de jeu : ${player.currentGame.hours}")

        }



    }
