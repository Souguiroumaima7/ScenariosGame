package com.example.scenariosgames.CreationGame

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R
import java.util.Scanner

class CreateScenariosGame(val name: String,
                          val instructions: String,
                          val imageURL: String,
                          val numberOfPlayers: Int,
                          val timerHours: Int) : ComponentActivity() {
        class screnarios {

        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_create_scenarios)
        }

        fun createscenarios(): CreateScenariosGame {
            val scanner = Scanner(System.`in`)

            println("Création d'un nouveau scenarios :")

            // Saisie du nom du jeu
            print("Nom du scenarios : ")
            val name = scanner.nextLine()

            // Saisie des instructions du jeu
            print("Instructions : ")
            val instructions = scanner.nextLine()

            // Saisie de l'URL de l'image
            print("URL de l'image : ")
            val imageURL = scanner.nextLine()

            // Saisie du nombre de joueurs
            print("Nombre de joueurs : ")
            val numberOfPlayers = scanner.nextInt()

            // Saisie de la minuterie en heures
            print("Durée de la minuterie en heures : ")
            val timerHours = scanner.nextInt()

            // Ignorer le reste de la ligne
            scanner.nextLine()

            return CreateScenariosGame(name, instructions, imageURL, numberOfPlayers, timerHours)
        }

        fun main() {
            // Exemple de création d'un jeu
            val scenarios = createscenarios()

            // Affichage des détails du jeu
            println("Nom du scenarios: ${scenarios.name}")
            println("Instructions : ${scenarios.instructions}")
            println("URL de l'image : ${scenarios.imageURL}")
            println("Nombre de joueurs : ${scenarios.numberOfPlayers}")
            println("Minuterie en heures : ${scenarios.timerHours} heures")
        }
    }


