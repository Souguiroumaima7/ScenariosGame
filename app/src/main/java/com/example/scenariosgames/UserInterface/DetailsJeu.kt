package com.example.scenariosapplication.UserInterface


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scenariosgames.R
import kotlin.random.Random

class DetailsJeu : AppCompatActivity() {
    // Propriétés du jeu
    private var nombreMystere: Int = 0
    private var tentative: Int = 0
    private var victoire: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailsjeux)
    }

    // Fonction pour démarrer le jeu
    fun demarrer() {
        println("Bienvenue dans le jeu du nombre mystère !")
        nombreMystere = Random.nextInt(1, 101) // Génère un nombre aléatoire entre 1 et 100
        println("Un nombre mystère a été généré. À vous de le deviner !")

        while (!victoire) {
            val proposition = demanderProposition()
            if (proposition == nombreMystere) {
                victoire = true
                println("Félicitations ! Vous avez trouvé le nombre mystère en $tentative tentatives.")
            } else if (proposition < nombreMystere) {
                println("Le nombre mystère est plus grand.")
            } else {
                println("Le nombre mystère est plus petit.")
            }
        }
    }

    // Fonction pour demander une proposition au joueur
    private fun demanderProposition(): Int {
        print("Entrez votre proposition : ")
        val proposition = readLine()?.toIntOrNull()
        if (proposition == null) {
            println("Veuillez entrer un nombre valide.")
            return demanderProposition()
        }
        tentative++
        return proposition
    }
}

// Fonction principale
fun main() {
    val DetailsJeu = DetailsJeu()
    DetailsJeu.demarrer()
}
