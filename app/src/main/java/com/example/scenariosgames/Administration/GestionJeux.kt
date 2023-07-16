package com.example.scenariosapplication.Administration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.R

// Classe représentant un joueur
data class Player(val name: String, var score: Int)

class GestionJeux  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jeux_update)
    }

    private val players = mutableListOf<Player>()
    // Ajouter un joueur
    fun addPlayer(player: Player) {
        players.add(player)
    }

    // Obtenir le score d'un joueur
    fun getPlayerScore(playerName: String): Int? {
        val player = players.find { it.name == playerName }
        return player?.score
    }

    // Mettre à jour le score d'un joueur
    fun updatePlayerScore(playerName: String, newScore: Int) {
        val player = players.find { it.name == playerName }
        player?.score = newScore
    }
}
// Exemple d'utilisation
fun main() {
    val GestionJeux = GestionJeux()

    val player1 = Player("Alice", 0)
    val player2 = Player("Bob", 0)

    GestionJeux.addPlayer(player1)
    GestionJeux.addPlayer(player2)

    GestionJeux.updatePlayerScore("Alice", 10)
    GestionJeux.updatePlayerScore("Bob", 5)

    println(GestionJeux.getPlayerScore("Alice")) // Affiche : 10
    println(GestionJeux.getPlayerScore("Bob")) // Affiche : 5
}