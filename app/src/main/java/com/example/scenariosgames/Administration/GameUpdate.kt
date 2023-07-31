package com.example.scenariosapplication.Administration

import android.os.Bundle
import androidx.activity.ComponentActivity


data class Player(val name: String, var score: Int)

class GameUpdate  : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val players = mutableListOf<Player>()

    fun addPlayer(player: Player) {
        players.add(player)
    }

    fun getPlayerScore(playerName: String): Int? {
        val player = players.find { it.name == playerName }
        return player?.score
    }


    fun updatePlayerScore(playerName: String, newScore: Int) {
        val player = players.find { it.name == playerName }
        player?.score = newScore
    }
}

fun main() {
    val Game = GameUpdate()

    val player1 = Player("Alice", 0)
    val player2 = Player("Bob", 0)

    Game.addPlayer(player1)
    Game.addPlayer(player2)

    Game.updatePlayerScore("Alice", 10)
    Game.updatePlayerScore("Bob", 5)

    println(Game.getPlayerScore("Alice")) // Affiche : 10
    println(Game.getPlayerScore("Bob")) // Affiche : 5
}