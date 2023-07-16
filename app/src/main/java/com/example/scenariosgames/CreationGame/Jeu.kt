package com.example.scenariosgames.CreationGame

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R

class Jeu(val nom: String, val scenario: String) : ComponentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_jeux)

        }
        fun demarrer() {
            println("Démarrage du jeu $nom")
            println("Scenario : $scenario")
        }
    }

    // Fonction principale
    fun main() {
        // Création d'une liste de jeux
        val jeux = mutableListOf<Jeu>()

        // Ajout de jeux à la liste
        jeux.add(Jeu("Jeu 1", "Scenario 1"))
        jeux.add(Jeu("Jeu 2", "Scenario 2"))
        jeux.add(Jeu("Jeu 3", "Scenario 3"))

        // Exploration des jeux
        for (jeu in jeux) {
            jeu.demarrer()
            // Effectuer d'autres actions spécifiques au jeu
            println("Fin du jeu ${jeu.nom}\n")
        }
    }


