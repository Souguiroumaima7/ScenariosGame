package com.example.scenariosgames.CreationGame

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.scenariosgames.R
import java.util.Scanner

class GameActivity(
    val name: String,
    val instructions: String,
    val imageURL: String,
    val numberOfPlayers: Int,
    val timerHours: Int) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jeux)
    }
}
fun main() {
    // Exemple de création d'un jeu
    val game = createGame()

    // Affichage des détails du jeu
    println("Nom du jeu : ${game.name}")
    println("Instructions : ${game.instructions}")
    println("URL de l'image : ${game.imageURL}")
    println("Nombre de joueurs : ${game.numberOfPlayers}")
    println("Minuterie en heures : ${game.timerHours} heures")
}

fun createGame(): GameActivity {
    val scanner = Scanner(System.`in`)

    println("Création d'un nouveau jeu :")

    // Saisie du nom du jeu
    print("Nom du jeu : ")
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

    return GameActivity(name, instructions, imageURL, numberOfPlayers, timerHours)
}



